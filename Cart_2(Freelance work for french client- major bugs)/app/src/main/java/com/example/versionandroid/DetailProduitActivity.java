package com.example.versionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.versionandroid.entities.ImageProduit;
import com.example.versionandroid.entities.Produit;

public class DetailProduitActivity extends AppCompatActivity {

    LinearLayout ll;
    TextView tv_prix, tv_quantite, tv_nomProduit, tv_description;
    ImageView img_produit;
    View navbar;

    Button btnPrev, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail_produit);
        //Ajout du navBar à la page
        ll = findViewById(R.id.navbar_detail_produit);
        LayoutInflater inflater = getLayoutInflater();
        navbar = inflater.inflate(R.layout.navbar, null);
        ll.addView(navbar);


        tv_prix = findViewById(R.id.tv_prix);
        tv_quantite = findViewById(R.id.tv_quantite);
        tv_nomProduit = findViewById(R.id.tv_nomProduit);
        img_produit = findViewById(R.id.img_produit);
        tv_description = findViewById(R.id.tv_description);


        String valeurPrix = getIntent().getStringExtra("prix");
        int valeurQte = getIntent().getIntExtra("quantite", 0);
        String valeurNomProduit = getIntent().getStringExtra("nomProduit");
        int urlImg = getIntent().getIntExtra("img", 0);
        String descriptionProduit = getIntent().getStringExtra("description");

        tv_prix.setText("Prix : " + valeurPrix);
        tv_quantite.setText("Quantité en stock : " + valeurQte);
        tv_nomProduit.setText(valeurNomProduit);
        img_produit.setImageResource(urlImg);
        tv_description.setText(descriptionProduit);


    }


}
