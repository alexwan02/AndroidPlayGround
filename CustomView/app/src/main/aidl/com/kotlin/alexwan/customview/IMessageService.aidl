// IMessageService.aidl
package com.kotlin.alexwan.customview;

// Declare any non-default types here with import statements
import com.kotlin.alexwan.customview.Message;
import com.kotlin.alexwan.customview.IMessageCallBack;

interface IMessageService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
    void send(in Message message);

    void registerCallBack(in IMessageCallBack callback);
}
