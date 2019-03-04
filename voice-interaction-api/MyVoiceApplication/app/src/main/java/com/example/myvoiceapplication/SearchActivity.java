package com.example.myvoiceapplication;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.actions.SearchIntents;

public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if(SearchIntents.ACTION_SEARCH.equals(intent.getAction())){
            String query   = intent.getStringExtra(SearchManager.QUERY);
            Log.d("TAG", "can do my search");
        }
    }
}
