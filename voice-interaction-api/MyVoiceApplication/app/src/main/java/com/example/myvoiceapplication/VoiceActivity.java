package com.example.myvoiceapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class VoiceActivity extends Activity {
    private static String TAG = "VOICE_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate :");
    }

    @Override
    public boolean onSearchRequested() {
        Log.d(TAG, "search requested");
        return super.onSearchRequested();
    }
}
