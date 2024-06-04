package com.example.tlu.cse.ht63.coffeeshop.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import com.example.tlu.cse.ht63.coffeeshop.Models.Product;

import java.util.ArrayList;
import java.util.List;

public class DbProductHelper extends SQLiteOpenHelper {
    private static final String DATABASENAME = "MyDatabase.db";
    private static final int DATABASEVERSION = 1;
    private final String TABLE_NAME = "Product";
    private final String COLUMN_ID = "id";
    private final String COLUMN_NAME_PRODUCT = "name_product";
    private final String COLUMN_DESCRIPTION = "description";
    private final String COLUMN_IMAGE = "image";
    private final String COLUMN_PRICE = "price";

    public DbProductHelper(Context context) {
        super(context, "product.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("create TABLE %s(\n" +
                "    %s INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    %s NVARCHAR(255) not null unique,\n" +
                "    %s NVARCHAR(255) not null,\n" +
                "    %s INTEGER not null,\n" +
                "    %s float not NULL\n" +
                ")", TABLE_NAME, COLUMN_ID, COLUMN_NAME_PRODUCT, COLUMN_DESCRIPTION, COLUMN_IMAGE, COLUMN_PRICE));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_PRODUCT, product.getName_product());
        contentValues.put(COLUMN_DESCRIPTION, product.getDescription());
        contentValues.put(COLUMN_IMAGE, product.getImage());
        contentValues.put(COLUMN_PRICE, product.getPrice());
        return db.insert(TABLE_NAME, null, contentValues) > 0;
    }

    public List<Product> getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Product> productList = new ArrayList<>();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s", TABLE_NAME), null);

        while (cursor.moveToNext()){
            int indexID = cursor.getColumnIndex(COLUMN_ID);
            int indexNameProduct = cursor.getColumnIndex(COLUMN_NAME_PRODUCT);
            int indexDescription = cursor.getColumnIndex(COLUMN_DESCRIPTION);
            int indexImage = cursor.getColumnIndex(COLUMN_IMAGE);
            int indexPrice = cursor.getColumnIndex(COLUMN_PRICE);

            int id = cursor.getInt(indexID);
            String nameProduct = cursor.getString(indexNameProduct);
            String description = cursor.getString(indexDescription);
            int image = cursor.getInt(indexImage);
            float price = cursor.getFloat(indexPrice);

            Product product = new Product(id, nameProduct, description, image, price);
            productList.add(product);
        }
        return productList;
    }

    public Product getByID(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = %d", TABLE_NAME, COLUMN_ID, id), null);
        cursor.moveToFirst();
        int indexID = cursor.getColumnIndex(COLUMN_ID);
        int indexNameProduct = cursor.getColumnIndex(COLUMN_NAME_PRODUCT);
        int indexDescription = cursor.getColumnIndex(COLUMN_DESCRIPTION);
        int indexImage = cursor.getColumnIndex(COLUMN_IMAGE);
        int indexPrice = cursor.getColumnIndex(COLUMN_PRICE);

        String nameProduct = cursor.getString(indexNameProduct);
        String description = cursor.getString(indexDescription);
        int image = cursor.getInt(indexImage);
        float price = cursor.getFloat(indexPrice);

        return new Product(id, nameProduct, description, image, price);
    }
}
