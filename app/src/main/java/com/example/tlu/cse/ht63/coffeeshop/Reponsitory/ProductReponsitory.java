package com.example.tlu.cse.ht63.coffeeshop.Reponsitory;

import android.content.Context;

import com.example.tlu.cse.ht63.coffeeshop.Database.DbProductHelper;
import com.example.tlu.cse.ht63.coffeeshop.Models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductReponsitory {
    private static List<Product> products;
    DbProductHelper db;
    public ProductReponsitory(Context context){
        products = new ArrayList<>();
        db = new DbProductHelper(context);
        products = db.getAll();
    }

    public void insert(Product product){
        products.add(product);
        db.insertProduct(product);
    }
     public List<Product> getAll(){
         return products;
     }
     public boolean add(Product product){
         products.add(product);
         return db.insertProduct(product);
     }
}
