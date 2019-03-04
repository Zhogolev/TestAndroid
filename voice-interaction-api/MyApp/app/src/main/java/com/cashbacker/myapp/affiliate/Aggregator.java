package com.cashbacker.myapp.affiliate;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Aggregator {
    private static final String TAG = "Aggregator";
    public static String getLink(JSONArray jsonArray, String merchant) throws JSONException {
        final int length = jsonArray.length();
        String deeplink = "";
        Log.d(TAG, merchant);
        for(int i = 0;  i < length; i++){
            JSONObject js =  jsonArray.getJSONObject(i);
            boolean condition = js.getString("site").contains(merchant);
            if(condition){
                deeplink =  js.getString("deep_link");
                Log.d(TAG, deeplink);
                return deeplink;
            }
        }
        Log.d(TAG, "NOT FIND DEEP LINK");

        return deeplink;
    }
}
