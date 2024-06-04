package com.example.tlu.cse.ht63.coffeeshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlu.cse.ht63.coffeeshop.Adapter.ProductAdapter;
import com.example.tlu.cse.ht63.coffeeshop.Models.Product;
import com.example.tlu.cse.ht63.coffeeshop.R;
import com.example.tlu.cse.ht63.coffeeshop.Reponsitory.ProductReponsitory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ProductAdapter adapter;
    ProductReponsitory reponsitory;
    ArrayList<Product> cartList = new ArrayList<>();
    ImageView btnCart;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //clear SharedPreferences
        this.getSharedPreferences("data", this.MODE_PRIVATE).edit().clear().apply();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        reponsitory = new ProductReponsitory(this);
//        initDataProduct();
        recyclerView = findViewById(R.id.recyclerViewProduct);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        adapter = new ProductAdapter(this, reponsitory.getAll(), cartList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        // cart
        btnCart = findViewById(R.id.btn_cart);
        btnCart.setOnClickListener(v -> {
           Intent intent = new Intent(MainActivity.this, CartActivity.class);
           Bundle bundle = new Bundle();
           bundle.putSerializable("cartList", cartList);
           intent.putExtras(bundle);
           startActivity(intent);
//           intent.putParcelableArrayListExtra("cartList", cartList);

        });
    }

    private void initDataProduct() {
        int[] images = {R.drawable.image2, R.drawable.image, R.drawable.anh2, R.drawable.birthday, R.drawable.ic_launcher_background};
        Random rd = new Random();
        for (int i = 0; i < 10; i++){
            int image = images[ rd.nextInt(images.length) ];
            String description = "Description " + (i+1);
            String name = "Product " + (i+1);
            float price = 10 + rd.nextInt(100- 10);
            reponsitory.add(new Product(-1, name, description, image, price));

        }
    }
}