package com.demoapp.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.demoapp.R;
import com.demoapp.Utils.Constants;
import com.demoapp.adapter.ProductsAdapter;
import com.demoapp.model.Product;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private ProductsAdapter mProductsAdapter;
    private Toolbar mToolbar;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mTextView = (TextView) findViewById(R.id.empty_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar();
        getProducts();
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        mToolbar.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(getString(R.string.search_list));
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getProducts() {
        if (getIntent() != null) {
            ArrayList<Product> productArrayList = (ArrayList<Product>) getIntent().getExtras().getSerializable(Constants.PRODUCTS);
            setRecyclerAdapter(productArrayList);
        }
    }

    private void setRecyclerAdapter(ArrayList<Product> productArrayList) {
        if (productArrayList.size() > 0) {
            RecyclerView recyclerProducts = (RecyclerView) findViewById(R.id.recycler_products);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerProducts.setLayoutManager(layoutManager);
            mProductsAdapter = new ProductsAdapter(productArrayList);
            recyclerProducts.setAdapter(mProductsAdapter);
        } else {
            mTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mProductsAdapter != null)
            mProductsAdapter.clear();
        mProductsAdapter = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return (super.onOptionsItemSelected(menuItem));
    }
}
