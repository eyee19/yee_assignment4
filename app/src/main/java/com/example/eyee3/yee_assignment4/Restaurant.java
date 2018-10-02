package com.example.eyee3.yee_assignment4;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private String name;
    private String phone;
    private String website;
    private float rating;
    private String category;

    Restaurant(String n, String p, String w, float r, String c) {
        name = n;
        phone = p;
        website = w;
        rating = r;
        category = c;
    }

    String getName() {
        return name;
    }

    String getPhone() {
        return phone;
    }

    String getWebsite() {
        return website;
    }

    float getRating() {
        return rating;
    }

    String getCategory() {
        return  category;
    }
}

