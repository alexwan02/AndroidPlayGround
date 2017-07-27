package com.kotlin.alexwan.customview;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by alexwan on 27/07/2017.
 */

public class Message implements Parcelable {
    public String from;
    public String to;
    public String content;

    public Message(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    private Message(Parcel in) {
        readFromParcel(in);
    }


    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(from);
        dest.writeString(to);
        dest.writeString(content);
    }


    public void writeToParcel(Parcel data) {
        data.writeString(from);
        data.writeString(to);
        data.writeString(content);
    }


    public void readFromParcel(Parcel in) {
        from = in.readString();
        to = in.readString();
        content = in.readString();
    }

    @Override
    public String toString() {
        return "message : from = " + from
                + " \nto = " + to
                + "\ncontent = " + content;
    }
}
