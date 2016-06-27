package com.demoapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rupesh on 22/6/16.
 */
public class Product implements Serializable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String name;
    @SerializedName("url")
    private String imageUrl;
    private String price;
}
