package com.example.versionandroid.manager;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.versionandroid.entities.Produit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProduitManager {
    public static List<Produit> getAll(Context ctx) {

        ArrayList<Produit> produits = new ArrayList<>();
        String json;
        try {

            InputStream is = ctx.getAssets().open("products.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                produits.add(new Produit(jsonObject.getDouble("prix"), jsonObject.getString("imgName"), jsonObject.getString("name"), jsonObject.getInt("quantite"), jsonObject.getString("description")));
                //produits.add(new Produit(jsonObject.getString("description")));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return produits;
    }
}
