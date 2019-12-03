package com.example.versionandroid.entities;

import java.util.ArrayList;

public class ImageProduit {
    int idProduit;
    ArrayList<String>listeImage;

    public ImageProduit(int idProduit, ArrayList<String> listeImage) {
        this.idProduit = idProduit;
        this.listeImage = listeImage;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public ArrayList<String> getListeImage() {
        return listeImage;
    }

    public void setListeImage(ArrayList<String> listeImage) {
        this.listeImage = listeImage;
    }
}
