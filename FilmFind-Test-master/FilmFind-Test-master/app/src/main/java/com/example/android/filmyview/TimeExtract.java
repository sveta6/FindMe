package com.example.android.filmyview;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class TimeExtract {

    public String[] Extract(String urls) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] str = new String[2];
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(urls);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200) {
                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in, Charset.forName("UTF-8"));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String string = bufferedReader.readLine();

                while (string != null) {
                    stringBuilder.append(string);
                    string = bufferedReader.readLine();
                }

                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                str[0] = jsonObject.getString("runtime");
                JSONArray jsonArray = jsonObject.getJSONArray("genres");

                for (int l = 0; l < jsonArray.length(); l++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(l);
                    if (str[1] == "" || str[1] == null) {
                        str[1] = jsonObject1.getString("name");
                    } else {
                        str[1] = str[1] + " , " + jsonObject1.getString("name");
                    }
                }
            }

        } catch (Exception e) {
            Log.w("Error", e.toString());
        } finally {
            httpURLConnection.disconnect();
        }
        return str;
    }
}
