package com.kotlin.alexwan.customview;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IMessageService mMessageService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler(getMainLooper());

        bindService();

        final EditText editText = (EditText) findViewById(R.id.message);

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String content = editText.getText().toString();
                    mMessageService.send(new Message("lucky" , "alex" , TextUtils.isEmpty(content) ? "你好" : content));
                } catch (RemoteException e) {
                    Log.e(MainActivity.class.getSimpleName() , "error = " + e.getMessage());
                }
            }
        });
    }

    private void bindService() {
        Intent intent = new Intent(this,  MessageService.class);
        intent.setAction("com.kotlin.alexwan.customview.IMessageService");
        bindService(intent , mConnection , Context.BIND_AUTO_CREATE);
    }

    private Handler mHandler;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessageService = IMessageService.Stub.asInterface(service);
            try {
                mMessageService.registerCallBack(new IMessageCallBack.Stub() {
                    @Override
                    public void onReceiveMessage(final Message message) throws RemoteException {
                        Log.i(MainActivity.class.getSimpleName()  , "onReceiveMessage : pid = " + Process.myPid() + " ; thread = " + Thread.currentThread().getName());
                        Log.i("MainActivity" , "onReceiveMessage : " + message.toString());
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, message.toString() , Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
            } catch (RemoteException e) {
                Log.e(MainActivity.class.getSimpleName() , "error = "+ e.getMessage());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMessageService = null;
        }
    };


}
