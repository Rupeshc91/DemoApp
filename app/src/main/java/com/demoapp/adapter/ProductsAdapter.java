package com.demoapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demoapp.R;
import com.demoapp.model.Product;

import java.util.ArrayList;

/**
 * Created by rupesh on 23/6/16.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductHolder> {
    private ArrayList<Product> mProductList;

    public ProductsAdapter(ArrayList<Product> productArrayList) {
        mProductList = productArrayList;
    }


    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        Product product = mProductList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(product.getImageUrl())
                .placeholder(R.drawable.ic_launcher)
                .into(holder.imageProduct);
        holder.textProductName.setText(product.getName());
        StringBuilder price = new StringBuilder("Rs. ");
        price.append(product.getPrice());
        holder.textProductPrice.setText(price.toString());
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        private ImageView imageProduct;
        private TextView textProductName;
        private TextView textProductPrice;

        public ProductHolder(View itemView) {
            super(itemView);
            imageProduct = (ImageView) itemView.findViewById(R.id.image_product);
            textProductName = (TextView) itemView.findViewById(R.id.text_product_name);
            textProductPrice = (TextView) itemView.findViewById(R.id.text_product_price);
        }
    }

    public void clear() {
        mProductList.clear();
    }
}

