package com.example.lukas.newsapp;

import android.graphics.Bitmap;

public class Article {
    private Bitmap mImage;
    private String mText;
    private String mDate;
    private String mDescription;
    private String mURL;
    private Bitmap mLargeImage;
    private String mImageUrl;

    public String getmDescription() {
        return mDescription;
    }

    public String getmURL() {
        return mURL;
    }

    public Bitmap getmLargeImage() {
        return mLargeImage;
    }

    public Article(Bitmap image, String text, String date, String description, String URL,
                   Bitmap largeImage)
    {
        mImage = image;
        mText = text;
        mDate = date;
        mDescription = description;
        mURL = URL;
        mLargeImage = largeImage;
    }

    public Bitmap getmImage() {
        return mImage;
    }

    public String getmText() {
        return mText;
    }

    public void setmImage(Bitmap mImage) {
        this.mImage = mImage;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmDate() {
        return mDate;
    }
}
