package com.example.lukas.newsapp;

import android.widget.ImageView;

public interface ImageLoader {
    void loadThumbnail(String url, ImageView imageView);
    void load(String url, ImageView imageview);
}
