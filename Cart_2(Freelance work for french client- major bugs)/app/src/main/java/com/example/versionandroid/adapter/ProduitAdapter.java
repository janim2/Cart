package com.example.versionandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.versionandroid.DetailProduitActivity;
import com.example.versionandroid.MainActivity;
import com.example.versionandroid.Panier;
import com.example.versionandroid.R;
import com.example.versionandroid.entities.Produit;
import com.example.versionandroid.manager.MySharedPreference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProduitAdapter extends ArrayAdapter<Produit> {
    int layout;
    Context ctx;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private List<Produit> allProducts;
    private Gson gson;
    private int cartProductNumber = 0;
    public ProduitAdapter(@NonNull Context context, int resource, @NonNull List<Produit> objects) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_produits, null);
        }

        ImageView img = convertView.findViewById(R.id.imgprod);
        TextView tvname = convertView.findViewById(R.id.nameprod);
        TextView tvprice = convertView.findViewById(R.id.priceprod);
        TextView tvQty = convertView.findViewById(R.id.qtyprod);
        Button btnAdd=convertView.findViewById(R.id.addtoShop);
        img.setImageResource(produit.getUrlImg());
        tvname.setText(produit.getName());
        tvprice.setText("Prix: " + String.valueOf(produit.getPrix()));
        tvQty.setText("Quantite: " + String.valueOf(produit.getQuantite()));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                preferences= PreferenceManager.getDefaultSharedPreferences(ctx);
//                editor=preferences.edit();
//                String nom= produit.getName();
//                String prix=produit.getPrix().toString();
//                editor.putString("nom",nom);
//                editor.putString("prix",prix);
//                editor.commit();
//                Toast.makeText(getContext(),"ADD",Toast.LENGTH_LONG).show();
//                Intent intent=new Intent(getContext(), Panier.class);
//               // intent.putExtra("nom", produit.getName());
//                //intent.putExtra("price", produit.getPrix());
//                //intent.putExtra("qty", produit.getQuantite());
//                ctx.startActivity(intent);
                GsonBuilder builder = new GsonBuilder();
                gson = builder.create();

                final Produit single__Product = allProducts.get(position);
                if(single__Product == null){
                }
                String stringObjectRepresentation = gson.toJson(single__Product);

                MySharedPreference sharedPreference = new MySharedPreference(ctx);
                String productsFromCart = sharedPreference.retrieveProductFromCart();

                Intent intent = ((Activity) ctx).getIntent();
                String productInStringFormat = stringObjectRepresentation;
                final Produit singleProduct = gson.fromJson(productInStringFormat, Produit.class);
                if(productsFromCart.equals("")){
                    List<Produit> cartProductList = new ArrayList<Produit>();
                    cartProductList.add(singleProduct);
                    String cartValue = gson.toJson(cartProductList);
                    sharedPreference.addProductToTheCart(cartValue);
                    cartProductNumber = cartProductList.size();
                }else{
                    String productsInCart = sharedPreference.retrieveProductFromCart();
                    Produit[] storedProducts = gson.fromJson(productsInCart, Produit[].class);

                    List<Produit> allNewProduct = convertObjectArrayToListObject(storedProducts);
                    allNewProduct.add(singleProduct);
                    String addAndStoreNewProduct = gson.toJson(allNewProduct);
                    sharedPreference.addProductToTheCart(addAndStoreNewProduct);
                    cartProductNumber = allNewProduct.size();
                }
                sharedPreference.addProductCount(cartProductNumber);
                invalidateCart();
                Toast.makeText(v.getContext(),"Added to Cart", Toast.LENGTH_LONG).show();
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailProduitActivity.class);
                intent.putExtra("nomProduit", produit.getName());
                intent.putExtra("prix", produit.getPrix().toString());
                intent.putExtra("quantite", produit.getQuantite());
                intent.putExtra("img", produit.getUrlImg());
                intent.putExtra("description", produit.getDescription());
                ctx.startActivity(intent);
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
