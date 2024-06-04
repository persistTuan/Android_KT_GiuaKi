package com.example.tlu.cse.ht63.coffeeshop.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlu.cse.ht63.coffeeshop.Models.Product;
import com.example.tlu.cse.ht63.coffeeshop.R;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    List<Product> productList;
    List<Product> cartList;
    Context context;
    SharedPreferences shareData;

    public ProductAdapter(Context context, List<Product> productList, List<Product> cartList) {
        this.context = context;
        this.cartList = cartList;
        this.productList = productList;
        shareData = context.getSharedPreferences("data", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override

    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.productImage.setImageResource( product.getImage()) ;
        holder.productDescription.setText(product.getDescription());
        holder.productPrice.setText("đ " + product.getPrice());
        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cartList.contains(product)){
                    cartList.add(product);
                }
                else{
                    Product tmp = cartList.get(0);
                    if(tmp.getId() != product.getId()){
                        cartList.remove(product);
                        cartList.add(0, product);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productDescription, productPrice;
        Button addToCartButton;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productDescription = itemView.findViewById(R.id.textProductDescription);
            productPrice = itemView.findViewById(R.id.textProductPrice);
            addToCartButton = itemView.findViewById(R.id.buttonProductAddToCart);
        }
    }
}