package com.cashbacker.myapp;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.cashbacker.myapp.tasks.GetInterstishialLinkTask;


public class VoiceActivity extends Activity {
    private static final String TAG = "VoiceActivity";
    private final String affLink = "https://rungrinh.vn/api/offer?fields=site,deep_link";
    private String query = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Log.d(TAG, "start activity");
        String query = intent.getStringExtra(SearchManager.QUERY);
        this.query = query;
        setContentView(R.layout.activity_voice);
        TextView text = findViewById(R.id.text_voice);
        text.setText(query);
        query = query.replace(" ", "");
        GetInterstishialLinkTask getTask = new GetInterstishialLinkTask(this, query);
        getTask.execute();
        // Log.d(TAG, query);
    }
    //}

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "on new intent");
        super.onNewIntent(intent);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "on start");
        super.onStart();
    }

    public void onAsyncResult(String result) {
        Log.d(TAG, "return from async " + result);
        final String HTTP = "http://";
        final String HTTPS = "https://";

        String url = result;
        if (!url.isEmpty()) {
            if (!(url.contains(HTTP) || url.contains(HTTPS))) {
                url = HTTPS + url;
            }
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(myIntent);
        } else {
            Intent intentSearch = new Intent("android.intent.action.WEB_SEARCH", null);
            intentSearch.putExtra("query", this.query);
            startActivity(intentSearch);
            // todo here we can get a list of our merchants
            Log.d(TAG, "this is empty link");

        }
    }
}
