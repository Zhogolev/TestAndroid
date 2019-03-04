package com.cashbacker.myapp.tasks;

import android.os.AsyncTask;

import com.cashbacker.myapp.VoiceActivity;
import com.cashbacker.myapp.affiliate.Aggregator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class GetInterstishialLinkTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = "GetInterstialLink";
    private VoiceActivity parent;
    private String param;
    private URL url;
    HttpsURLConnection connection;

    {
        try {
            url = new URL("https://rungrinh.vn/api/offer?fields=site,deep_link");
            connection = (HttpsURLConnection) url.openConnection();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    String responseString = null;

    public GetInterstishialLinkTask(VoiceActivity parent, String param) {
        this.parent = parent;
        this.param = param;
    }

    @Override
    protected String doInBackground(Void... voids) {
        // todo change default value
        String result = "";
        String query = this.param;
        BufferedReader reader;

        if (true) {

            String[] splitQuery = query.split(" ");
            String merchant = splitQuery[splitQuery.length - 1];
            if (true) {

                try {
                    connection.setReadTimeout(10000);
                    connection.setRequestMethod("GET");
                    connection.connect();
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer buf = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        buf.append(line);
                    }
                    JSONObject returnedFromAffiliate = new JSONObject(buf.toString());
                    JSONArray jo = (JSONArray)returnedFromAffiliate.get("offers");
                    return Aggregator.getLink(jo, merchant);

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    @Override
    protected void onPostExecute(String s) {
        this.parent.onAsyncResult(s);
        super.onPostExecute(s);

    }

    private Boolean checkThatIsMerchant(String str) {
        return str.contains(".com") || str.contains(".fr") || str.contains(".net")
                || str.contains(".org") || str.contains(".ru") || str.contains(".pk");
    }
}
