package com.example.versionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.versionandroid.service.InscriptionHttpUrlConn;

import java.util.HashMap;

public class Inscription extends AppCompatActivity {
    LinearLayout ll;
    View navbar;
    EditText userName, nom, prenom, email, mdp, mdpConfirm;
    Button btnInscription;
    String strNom, strPrenom, strUsername, strEmail, strMdp;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_inscription);

        ctx = this;

        // Ajout du navbar à la page
        ll = findViewById(R.id.navbarinscription);
        LayoutInflater inflater = getLayoutInflater();
        navbar = inflater.inflate(R.layout.navbar, null);
        ll.addView(navbar);

        userName = findViewById(R.id.username_inscription);
        prenom = findViewById(R.id.ed_prenom);
        nom = findViewById(R.id.ed_nom);
        email = findViewById(R.id.ed_email);
        mdp = findViewById(R.id.ed_pwd_inscription);
        mdpConfirm = findViewById(R.id.ed_pwd_confirm);
        btnInscription = findViewById(R.id.btn_inscription);

        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmptyChampsTxt(prenom)) {
                    prenom.setError("Veuillez saisir le prénom");
                } else if (isEmptyChampsTxt(nom)) {
                    nom.setError("Veuillez saisir le nom");
                } else if (isEmptyChampsTxt(userName)) {
                    userName.setError("Veuillez saisir le username");
                } else if (isEmptyChampsTxt(email)) {
                    email.setError("Veuillez saisir votre email");
                } else if ((validationEmail(email) == false)) {
                    email.setError("Votre email n'est pas valide !");
                } else if (isEmptyChampsTxt(mdp)) {
                    mdp.setError(("Veuillez saisir le mot de passe"));
                } else if (mdp.getText().toString().length() < 4) {
                    mdp.setError("Le mot de passe doit contenir au moins 4 caractères");
                } else if (isEmptyChampsTxt(mdpConfirm)) {
                    mdpConfirm.setError("Veuillez saisir le mot de passe à nouveau");
                } else if (validationMdp2(mdp, mdpConfirm) == false) {
                    mdpConfirm.setError("Les mot de passes saisies doivent etre identiques !");
                } else {
                    //Client client = new Client(strUsername, strNom, strPrenom, strEmail);
                    insert();
                }
            }
        });

    }

    //Validation des données entrées par le client
    public static boolean isEmptyChampsTxt(EditText ed) {
        String str = ed.getText().toString();
        return TextUtils.isEmpty(str);
    }

    private boolean validationEmail(EditText ed) {
        String email = ed.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean validationMdp2(EditText ed1, EditText ed2) {
        String str1 = ed1.getText().toString();
        String str2 = ed2.getText().toString();
        if (str1.equals(str2)) {
            return true;
        } else {
            return false;
        }
    }


    ///
    private void insert() {
        strUsername = userName.getText().toString().trim();
        strNom = nom.getText().toString().trim();
        strPrenom = prenom.getText().toString().trim();
        strEmail = email.getText().toString().trim();
        strMdp = mdp.getText().toString().trim();
        class Insert extends AsyncTask<String, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected String doInBackground(String... strings) {
                HashMap<String, String> parametres = new HashMap<>();
                HashMap<String, String> hashMapUser = new HashMap<>();

                //Client user = new Client(strUsername, strEmail, strNom, strPrenom);

                hashMapUser.put("userName", strUsername);
                hashMapUser.put("email", strEmail);
                hashMapUser.put("lastName", strNom);
                hashMapUser.put("firstName", strPrenom);

                parametres.put("user", String.valueOf(hashMapUser));
                parametres.put("pwd", strMdp);

                InscriptionHttpUrlConn insHttpUrlConn = new InscriptionHttpUrlConn();
                String hc = insHttpUrlConn.envoiDonnee("http://69.159.182.204:9876/isserver/users", parametres);
                return hc;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(ctx, "Enregistrement...", "En cours...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                Intent intent = new Intent(ctx, ConfirmationInscription.class);
                startActivity(intent);
            }
        }

        Insert in = new Insert();
        in.execute();

    }
}
