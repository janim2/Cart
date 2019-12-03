package com.example.versionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class CreationNouveauMdp extends AppCompatActivity {
    LinearLayout ll;
    View navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_creation_nouveau_mdp);
        ll = findViewById(R.id.navbarCreationMdp);
        LayoutInflater inflater = getLayoutInflater();
        navbar = inflater.inflate(R.layout.navbar, null);
        ll.addView(navbar);
    }
}
