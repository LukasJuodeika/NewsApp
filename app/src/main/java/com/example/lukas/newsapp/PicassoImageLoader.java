package com.example.lukas.newsapp;

import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoImageLoader implements ImageLoader{

    @Override
    public void loadThumbnail(String url, ImageView imageView) {
        try {
            Picasso.get().load(url).resize(100,100).into(imageView);
        }
        catch (Exception e)
        {
            Log.d("picassoloader" ,e.getLocalizedMessage());
        }

    }

    @Override
    public void load(String url, ImageView imageView) {
        Picasso.get().load(url).into(imageView);
    }
}
