package com.kotlin.alexwan.customview;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by alexwan on 27/07/2017.
 */

public class MessageService extends Service {

    private IMessageCallBack mCallBack;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MessageService" , "info = onCreate");

        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                //mBinder.send();
                if(mCallBack != null){
                    
//                    try {
//                        // mCallBack.onReceiveMessage(new Message("alex" , "lucky" , "hello"));
//                    } catch (RemoteException e) {
//                        Log.e("MessageService" , "error = " + e.getMessage());
//                    }
//                    mCallBack.onReceiveMessage(Message.CREATOR.createFromParcel());
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task , 0 , 1000);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MessageService" , "info = onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    private IMessageService.Stub mBinder = new IMessageService.Stub() {

//        public int getPid(){
//            return Process.myPid();
//        }


        @Override
        public void send(Message message) throws RemoteException {
            Log.i("MessageService" , message.toString());
            mCallBack.onReceiveMessage(message);
        }

        @Override
        public void registerCallBack(IMessageCallBack callback) throws RemoteException {

            if(callback != null){
                Log.i("MessageService" , "info = registerCallBack");
                mCallBack = callback;
            }
        }
    };
}
