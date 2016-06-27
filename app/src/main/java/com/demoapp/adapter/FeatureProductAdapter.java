package com.demoapp.adapter;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demoapp.R;

/**
 * Created by rupesh on 24/6/16.
 */
public class FeatureProductAdapter extends android.support.v4.view.PagerAdapter {
    private int[] mFeatureProducts;

    public FeatureProductAdapter(int[] featureProducts) {
        mFeatureProducts = featureProducts;
    }

    @Override
    public int getCount() {
        return mFeatureProducts.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_feature_product, null);
        ImageView imageFeatureProduct = (ImageView) view.findViewById(R.id.image_feature_product);
        imageFeatureProduct.setImageResource(mFeatureProducts[position]);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        ViewPager pager = (ViewPager) container;
        View recycledView = (View) view;
        pager.removeView(recycledView);
    }

}
