package com.example.jsonparse.services;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class ResReceiver extends ResultReceiver {

    public static final int IDLE = 1;
    public static final int RUNNING = 2;
    public static final int ERROR = 3;

    private Receiver mReceiver;

    public ResReceiver(Receiver receiver) {
        super(new Handler());
        mReceiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }

    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }
}

