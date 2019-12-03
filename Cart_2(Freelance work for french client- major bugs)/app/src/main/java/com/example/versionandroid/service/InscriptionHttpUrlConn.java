package com.example.versionandroid.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class InscriptionHttpUrlConn {

    public String envoiDonnee(String requestURL, HashMap<String, String> postDataParams) {
        URL url;
        StringBuilder sb = new StringBuilder();//store message retrieved from the server


        try {
            url = new URL(requestURL); //Initilise l'url

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream(); //Crée un outputStream

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); //Écriture des paramètres
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();

            /*DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(getPostDataString(postDataParams));
            out.flush();
            out.close();*/

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                sb = new StringBuilder();
                String response;
                //Lit la réponse du serveur
                while ((response = br.readLine()) != null) {
                    sb.append(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return sb.toString();
    }

    public String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            sb.append("&");
        }

        return sb.toString();
    }
}
