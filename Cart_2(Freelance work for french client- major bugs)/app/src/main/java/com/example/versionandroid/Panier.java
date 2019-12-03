package com.example.versionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.versionandroid.adapter.PanierAdapter;
import com.example.versionandroid.adapter.ProduitAdapter;
import com.example.versionandroid.entities.Produit;
import com.example.versionandroid.manager.MySharedPreference;
import com.example.versionandroid.manager.ProduitManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Panier extends AppCompatActivity {
    LinearLayout ll;
    View navbar;
    ArrayList<Produit> produits = new ArrayList<>();
    Produit p;
    ListView lv;
    SharedPreferences preferences;
    ImageView imgPanier;
    Context ctx;
    TextView total_prize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_panier);
        ll = findViewById(R.id.navbar_panier);
        total_prize = findViewById(R.id.total);
        ctx=this;
        LayoutInflater inflater = getLayoutInflater();
        navbar = inflater.inflate(R.layout.navbar, null);
        ll.addView(navbar);
        imgPanier=navbar.findViewById(R.id.ipanier);
        imgPanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ctx,Panier.class);
                startActivity(intent);
            }
        });
        //p = new Produit();
        //p.setName(getIntent().getStringExtra("nom"));
       // p.setPrix(getIntent().getDoubleExtra("price", 1));
       // p.setQuantite(getIntent().getIntExtra("qty", 1));
        //getIntent().getDoubleExtra("price", 1)
        //getIntent().getStringExtra("nom")
        //getIntent().getIntExtra("qty", 1)
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
//        String title=preferences.getString("nom","");
//        Double prix=Double.parseDouble(preferences.getString("prix",""));
        lv = findViewById(R.id.lvpanier);
//
//        produits.add(new Produit(prix,title,1 ));
//        PanierAdapter panierAdapter = new PanierAdapter(this, R.layout.activity_panier, produits);
//
//        lv.setAdapter(panierAdapter);
//
//        panierAdapter.notifyDataSetChanged();

        // get content of cart
        MySharedPreference mShared = new MySharedPreference(Panier.this);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Produit[] addCartProducts = gson.fromJson(mShared.retrieveProductFromCart(), Produit[].class);
        List<Produit> productList = convertObjectArrayToListObject(addCartProducts);

        PanierAdapter panierAdapter = new PanierAdapter(this, R.layout.activity_panier, productList);
        lv.setAdapter(panierAdapter);

        total_prize.setText(String.valueOf(getTotalPrice(productList)));
    }

    private List<Produit> convertObjectArrayToListObject(Produit[] allProducts){
        List<Produit> mProduct = new ArrayList<Produit>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }

    private double getTotalPrice(List<Produit> mProducts){
        double totalCost = 0;
        for(int i = 0; i < mProducts.size(); i++){
            Produit pObject = mProducts.get(i);
            totalCost = totalCost + pObject.getPrix() + pObject.getQuantite();
        }
        return totalCost;
    }

}
