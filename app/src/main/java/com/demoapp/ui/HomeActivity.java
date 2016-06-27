package com.demoapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.demoapp.R;
import com.demoapp.Utils.Constants;
import com.demoapp.Utils.KeyboardUtils;
import com.demoapp.Utils.StringUtils;
import com.demoapp.adapter.FeatureProductAdapter;
import com.demoapp.model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private static final int ENGLISH = 0;
    private static final int HINDI = 1;
    private FrameLayout mFrameParallaxContainer;
    private ArrayList<Product> mProductArrayList;
    private MenuItem mSearchMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        initToolbar();
        initViews();
        loadProductsData(ENGLISH);
        initFeatureProductView();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
    }

    private void initViews() {
        mFrameParallaxContainer = (FrameLayout) findViewById(R.id.frame_parallax_container);
    }

    private void initFeatureProductView() {
        int[] featureProducts = {R.drawable.movie1, R.drawable.movie2, R.drawable.movie3};
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.feature_product_layout, null);
        ViewPager viewPager = (ViewPager) frameLayout.findViewById(R.id.viewPager);
        mFrameParallaxContainer.addView(frameLayout);
        FeatureProductAdapter featureProductAdapter = new FeatureProductAdapter(featureProducts);
        viewPager.setAdapter(featureProductAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mSearchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(mSearchMenuItem);
        setQueryTextListener(searchView);
        return true;
    }

    private void setQueryTextListener(final SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query)) {
                    final ArrayList<Product> productList = filter(mProductArrayList, query);
                    callSearchActivity(productList);
                    KeyboardUtils.hideKeyboard(getApplicationContext(), searchView);
                    searchView.setQuery("", false);
                    mSearchMenuItem.collapseActionView();
                    searchView.clearFocus();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    private void callSearchActivity(ArrayList<Product> productList) {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        intent.putExtra(Constants.PRODUCTS, productList);
        startActivity(intent);
    }

    private ArrayList<Product> filter(List<Product> productList, String query) {
        query = query.toLowerCase();
        final ArrayList<Product> filteredModelList = new ArrayList<>();
        for (Product product : productList) {
            final String text = product.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(product);
            }
        }
        return filteredModelList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_english:
                mSearchMenuItem.setVisible(true);
                loadProductsData(ENGLISH);
                break;
            case R.id.action_hindi:
                mSearchMenuItem.setVisible(false);
                loadProductsData(HINDI);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadProductsData(int languageType) {
        Type collectionType = new TypeToken<List<Product>>() {
        }.getType();
        String productsArray;
        if (languageType == ENGLISH) {
            productsArray = StringUtils.readJsonFile(getApplicationContext(), "products_english.json");
        } else {
            productsArray = StringUtils.readJsonFile(getApplicationContext(), "products_hindi.json");
        }
        if (!TextUtils.isEmpty(productsArray)) {
            mProductArrayList = new Gson().fromJson(productsArray, collectionType);
            loadFragment(mProductArrayList);
        }
    }

    private void loadFragment(ArrayList<Product> productArrayList) {
        HomeFragment homeFragment = HomeFragment.createInstance(productArrayList);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, homeFragment).commit();
    }


}
