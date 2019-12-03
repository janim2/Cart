package com.example.versionandroid.service;

import android.os.AsyncTask;
import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpGetRequest extends AsyncTask<String, String, String> {
    String server_response;


    @Override
    protected String doInBackground(String... strings) {

        URL url;
        HttpURLConnection urlConnection = null;


        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

          //  urlConnection.setReadTimeout(10000);
           // urlConnection.setConnectTimeout(15000);
            //urlConnection.setRequestMethod("POST");
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

           /* List<AbstractMap.SimpleEntry> params = new ArrayList<>();
            params.add(new AbstractMap.SimpleEntry("username", "mkante"));
            params.add(new AbstractMap.SimpleEntry("pwd", "mkante"));

            OutputStream os = urlConnection.getOutputStream();
           BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(params));
            writer.flush();
            writer.close();
            os.close();*/

            ////

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                server_response = readStream(urlConnection.getInputStream());
                Log.v("reponse", server_response);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return server_response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
       // Log.v("hi", server_response);

    }


    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }


}
