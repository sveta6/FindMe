package com.example.android.filmyview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ExtractJsonResponse {

    public static List<MData> extract(String stringURL) {

        List<MData> extractedData = null;
        JSONObject root;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        String jsonResponse = "";
        URL Link = null;
        Link = CreateUrlObject(stringURL);
        try {
            httpURLConnection = (HttpURLConnection) Link.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
                root = new JSONObject(jsonResponse);
                extractedData = extractList(root);
            }
        } catch (Exception e) {
            Log.v("Connection", e.toString());
        }
        return extractedData;
    }

    private static List<MData> extractList(JSONObject root) {
        List<MData> mData = new ArrayList<MData>();
        if (root != null) {
            try {
                JSONArray result = root.getJSONArray("results");
                int length = result.length();
                for (int l = 0; l < length; l++) {
                    JSONObject obj = result.getJSONObject(l);
                    String title = obj.getString("title");
                    String releaseYear = obj.getString("release_date");
                    String rating = obj.getString("vote_average");
                    String plot = obj.getString("overview");

                    String posterLink = obj.getString("poster_path");
                    posterLink = posterLink.substring(1, posterLink.length());
                    posterLink = "https://image.tmdb.org/t/p/w200/" + posterLink;
                    boolean adult = obj.getBoolean("adult");
                    int mId = obj.getInt("id");
                    URL purl = new URL(posterLink);
                    Bitmap bitmap = BitmapFactory.decodeStream(purl.openConnection().getInputStream());
                    mData.add(new MData(title, releaseYear, plot, Double.valueOf(rating), "Hi", "hi", "fff", bitmap, mId));
                }
            } catch (Exception json) {
                Log.w("Wrong Json Format", json.toString());
            }
        }
        return mData;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String reader = bufferedReader.readLine();
        while (reader != null) {
            output.append(reader);
            reader = bufferedReader.readLine();
        }
        return output.toString();
    }

    private static URL CreateUrlObject(String stringURL) {
        URL url = null;
        try {
            url = new URL(stringURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

}
