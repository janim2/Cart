package com.example.versionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.versionandroid.adapter.ProduitAdapter;
import com.example.versionandroid.entities.Produit;
import com.example.versionandroid.manager.ProduitManager;

public class MainActivity extends AppCompatActivity {
    LinearLayout ll;
    View navbar;
    private Context ctx;
    private ListView lv;
    ProduitAdapter produitAdapter;
    TextView tvInscription, tvConnexion;
    ImageView imgPanier;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ctx = this;

        ll = findViewById(R.id.mainnavbar);
        LayoutInflater inflater = getLayoutInflater();
        navbar = inflater.inflate(R.layout.navbar, null);
        ll.addView(navbar);
        imgPanier = navbar.findViewById(R.id.ipanier);
        imgPanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, Panier.class);
                startActivity(intent);
            }
        });
        tvInscription = findViewById(R.id.tv_inscription);
        tvConnexion = findViewById(R.id.tv_connexion);
        lv = findViewById(R.id.lvproduit);
        produitAdapter = new ProduitAdapter(this, R.layout.activity_main, ProduitManager.getAll(ctx));
        lv.setAdapter(produitAdapter);

        produitAdapter.notifyDataSetChanged();


        tvInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, Inscription.class);
                startActivity(intent);
            }
        });

        tvConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, Connexion.class);
                startActivity(intent);
            }
        });

    }


}
