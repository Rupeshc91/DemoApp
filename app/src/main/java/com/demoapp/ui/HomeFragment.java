package com.demoapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demoapp.R;
import com.demoapp.Utils.Constants;
import com.demoapp.adapter.ProductsAdapter;
import com.demoapp.model.Product;

import java.util.ArrayList;

/**
 * Created by rupesh on 22/6/16.
 */
public class HomeFragment extends Fragment {

    public static HomeFragment createInstance(ArrayList<Product> productArrayList) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.PRODUCTS, productArrayList);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        initViews(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initViews(View view) {
        RecyclerView recyclerProducts = (RecyclerView) view.findViewById(R.id.recycler_products);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerProducts.setLayoutManager(layoutManager);
        setRecyclerAdapter(recyclerProducts);
    }

    private void setRecyclerAdapter(RecyclerView recyclerView) {
        if (getArguments() != null) {
            ArrayList<Product> productArrayList = (ArrayList<Product>) getArguments().getSerializable(Constants.PRODUCTS);
            ProductsAdapter productsAdapter = new ProductsAdapter(productArrayList);
            recyclerView.setAdapter(productsAdapter);
        }
    }
}
