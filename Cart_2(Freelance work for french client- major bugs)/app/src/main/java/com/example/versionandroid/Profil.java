package com.example.versionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class Profil extends AppCompatActivity {
    LinearLayout ll;
    View navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ll = findViewById(R.id.navbarProfil);
        LayoutInflater inflater = getLayoutInflater();
        navbar = inflater.inflate(R.layout.navbar, null);
        ll.addView(navbar);
    }
}
