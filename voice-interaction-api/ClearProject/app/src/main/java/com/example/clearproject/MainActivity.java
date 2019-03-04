package com.example.clearproject;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAIN_ACTIVITY";
    private static final int SPEECH_REQUEST_CODE = 0;
    private static final String TAG_SPEACH_RESULT = "SPOKEN_TEXT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate:");
        setContentView(R.layout.activity_main);
        displaySpeechRecognizer();
    }

    // Create an intent that can start the Speech Recognizer activity
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {

        Log.d("MAIN_ACTIVITY", "request code: "  + requestCode
                + "; result code : " + resultCode
                + "; data : " + data.getData());

        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            // String spokenText = results.get(0);
            Log.d(TAG_SPEACH_RESULT, results.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
