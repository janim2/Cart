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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Connexion extends AppCompatActivity {
    LinearLayout ll;
    View navbar;
    EditText edUsername, edMdp;
    TextView tvMdpOublie;
    ImageView imgLogo;
    Button btnConnexion, btnInscription;
    Context ctx;

    String strUsername, strMdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_connexion);
        ctx = this;

        ll = findViewById(R.id.navbar_connexion);
        edUsername = findViewById(R.id.ed_username);
        edMdp = findViewById(R.id.ed_mdp);
        tvMdpOublie = findViewById(R.id.ed_mdp);
        btnConnexion = findViewById(R.id.btn_connexion);
        btnInscription = findViewById(R.id.btn_inscription);
        imgLogo = findViewById(R.id.logo);

        //Ajout du navbar à la page
        LayoutInflater inflater = getLayoutInflater();
        navbar = inflater.inflate(R.layout.navbar, null);


        ///Autres liens de la page
        btnInscription.setOnClickListener(new Click());
        tvMdpOublie.setOnClickListener(new Click());
        imgLogo.setOnClickListener(new Click());


        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strUsername = edUsername.getText().toString();
                strMdp = edMdp.getText().toString();

                if (Inscription.isEmptyChampsTxt(edUsername)){
                    edUsername.setError("Veuillez saisir votre username");
                }
                else if (Inscription.isEmptyChampsTxt(edMdp)){
                    edMdp.setError("Veuillez saisir votre mot de passe !");
                }
                else{
                    connexionAuCompte(strUsername, strMdp);
                }
            }
        });

    }

    private void connexionAuCompte(String username, String mdp){

    }


    public class Click implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_inscription:
                    Intent intent1 = new Intent(ctx, Inscription.class);
                    startActivity(intent1);
                    break;

                case R.id.tv_mdp_oublie:
                    Intent intent2 = new Intent(ctx, MotDePasseOublieActivity.class);
                    startActivity(intent2);
                    break;

                case R.id.logo:
                    Intent intent3 = new Intent(ctx, MainActivity.class);
                    startActivity(intent3);

                default:
                    Intent intent4 = new Intent(ctx, Connexion.class);
                    startActivity(intent4);
            }
        }
    }
}
