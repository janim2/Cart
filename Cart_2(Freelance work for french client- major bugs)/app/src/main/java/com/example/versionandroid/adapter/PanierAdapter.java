package com.example.versionandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.versionandroid.R;
import com.example.versionandroid.entities.Produit;
import com.example.versionandroid.manager.MySharedPreference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PanierAdapter extends ArrayAdapter<Produit> {
    int layout;
    Context ctx;
    private List<Produit> allProducts;
    private int cartProductNumber = 0;

    public PanierAdapter(@NonNull Context context, int resource, @NonNull List<Produit> objects) {
        super(context, resource, objects);
        layout = resource;
        ctx = context;
        allProducts = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Produit produit = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_panier, null);
        }
        TextView tvTitre=convertView.findViewById(R.id.panier_titre);
        TextView tvPrice=convertView.findViewById(R.id.panier_prix);
        final TextView tvQty=convertView.findViewById(R.id.panier_qty);
        Button tvadd =convertView.findViewById(R.id.panier_add);
        Button tvsub =convertView.findViewById(R.id.panier_sub);
        Button tvremove=convertView.findViewById(R.id.panier_remove);
        tvTitre.setText(produit.getName());
        tvQty.setText(String.valueOf(produit.getQuantite()));
        tvPrice.setText(String.valueOf(produit.getPrix())+"$");

        final int[] quantitey = {produit.getQuantite()};

        tvadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantitey[0] = quantitey[0] + 1;
                tvQty.setText(String.valueOf(quantitey[0]));
            }
        });

        tvsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantitey[0] > 1){
                quantitey[0] = quantitey[0] - 1;
                tvQty.setText(String.valueOf(quantitey[0]));
            }
            }
        });

        tvremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                final Produit single__Product = allProducts.get(position);
                String stringObjectRepresentation = gson.toJson(single__Product);

                MySharedPreference sharedPreference = new MySharedPreference(ctx);
                String productsFromCart = sharedPreference.retrieveProductFromCart();
                String productInStringFormat = stringObjectRepresentation;
                final Produit singleProduct = gson.fromJson(productInStringFormat, Produit.class);
                if(productsFromCart.equals("")){
                    List<Produit> cartProductList = new ArrayList<Produit>();
                    cartProductList.remove(singleProduct);
                    String cartValue = gson.toJson(cartProductList);
                    sharedPreference.removeProductFromTheCart(cartValue);
                    cartProductNumber = cartProductList.size();
                }
                else{
                String productsInCart = sharedPreference.retrieveProductFromCart();
                Produit[] storedProducts = gson.fromJson(productsInCart, Produit[].class);

                List<Produit> allNewProduct = convertObjectArrayToListObject(storedProducts);
//                allNewProduct.remove(singleProduct);
                String addAndStoreNewProduct = gson.toJson(allNewProduct);
                sharedPreference.removeProductFromTheCart(addAndStoreNewProduct);
                cartProductNumber = allNewProduct.size();
            }
                sharedPreference.addProductCount(cartProductNumber);
//                invalidateCart();
                Toast.makeText(v.getContext(),"Removed From Cart", Toast.LENGTH_LONG).show();

        }
        });


        return convertView;
    }

    private List<Produit> convertObjectArrayToListObject(Produit[] allProducts){
        List<Produit> mProduct = new ArrayList<Produit>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }


    private void invalidateCart() {
        ActivityCompat.invalidateOptionsMenu((Activity) ctx);    }
}
