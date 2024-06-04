package com.example.tlu.cse.ht63.coffeeshop.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlu.cse.ht63.coffeeshop.Adapter.CartAdapter;
import com.example.tlu.cse.ht63.coffeeshop.Models.Product;
import com.example.tlu.cse.ht63.coffeeshop.R;
import com.example.tlu.cse.ht63.coffeeshop.databinding.ActivityCartBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    static ArrayList<Product> cartList = new ArrayList<>();
    RecyclerView recyclerView;
    TextView textViewEmptyCart;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cart), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActivityCartBinding binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cartList = (ArrayList<Product>) getIntent().getExtras().getSerializable("cartList");

        // get intent
        if(cartList.size() == 0){
            binding.textViewEmptyCart.setVisibility(View.VISIBLE);
        }
        else{
            Toast.makeText(this, "Size: " + cartList.size(), Toast.LENGTH_SHORT).show();
            cartAdapter = new CartAdapter(this, cartList);
            binding.textViewEmptyCart.setVisibility(View.INVISIBLE);
            binding.recyclerViewCart.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            binding.recyclerViewCart.setHasFixedSize(true);
            binding.recyclerViewCart.setAdapter(cartAdapter);
        }
        binding.btnBack.setOnClickListener(v ->{
            finish();
        });

    }
}