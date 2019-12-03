package com.example.versionandroid.entities;

import com.example.versionandroid.R;

import java.lang.reflect.Field;

public class Produit {
    private int id_produit;
    private int id_categorie;
    private Double prix;
    private String description;
    private String imgname;
    private String name;
    private int quantite;
    private int urlImg;

    public Produit() {
    }

    public Produit(Double prix, String name, int quantite) {
        this.prix = prix;
        this.name = name;
        this.quantite = quantite;
    }

    public Produit(Double prix, String imgname, String name, int quantite, String description) {
        try {

            if (imgname != null) {
                Field f = R.drawable.class.getField(imgname);
                try {
                    if (imgname != null)
                        this.setUrlImg(f.getInt(null));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        this.name = name;
        this.prix = prix;
        this.quantite = quantite;
        this.description = description;

    }

    /*public Produit(String imgname, String name, int quantite, String description) {
        this.prix = prix;
        this.imgname = imgname;
        this.name = name;
        this.quantite = quantite;
        this.description = description;
    }*/


    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getImgname() {
        return imgname;
    }

    public void setImgname(String imgname) {
        this.imgname = imgname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(int urlImg) {
        this.urlImg = urlImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
