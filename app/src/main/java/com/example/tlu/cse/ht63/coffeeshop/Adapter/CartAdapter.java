package com.example.tlu.cse.ht63.coffeeshop.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlu.cse.ht63.coffeeshop.Models.Product;
import com.example.tlu.cse.ht63.coffeeshop.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    Context context;
    List<Product> productList;
    SharedPreferences shareData;

    public CartAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        shareData = context.getSharedPreferences("data", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.CartDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!holder.CartQuantity.getText().toString().equals("1")){
                    int quantity = Integer.parseInt(holder.CartQuantity.getText().toString());
                    quantity--;
                    holder.CartQuantity.setText(String.valueOf(quantity));
                    shareData.edit().putString(product.getId() + "", quantity+"").apply();
                }
            }
        });

        holder.CartUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(holder.CartQuantity.getText().toString());
                quantity++;
                holder.CartQuantity.setText(String.valueOf(quantity));
                shareData.edit().putString(product.getId()+"", quantity+"").apply();
            }
        });

        holder.CartImageView.setImageResource(product.getImage());
        holder.CartDescription.setText(product.getDescription());
        holder.CartPrice.setText(String.valueOf(product.getPrice()));
        holder.CartQuantity.setText(shareData.getString(product.getId()+"", "1"));
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        ImageView CartImageView, CartUp, CartDown;
        TextView CartDescription, CartPrice, CartQuantity;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            CartImageView = itemView.findViewById(R.id.cart_image);
            CartDescription = itemView.findViewById(R.id.cart_description);
            CartPrice = itemView.findViewById(R.id.cart_price);
            CartQuantity = itemView.findViewById(R.id.cart_quantity);
            CartUp = itemView.findViewById(R.id.btn_up);
            CartDown = itemView.findViewById(R.id.btn_down);
        }
    }
}
