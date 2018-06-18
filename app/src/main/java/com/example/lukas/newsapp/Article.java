package com.example.lukas.newsapp;

import android.graphics.Bitmap;

public class Article {
    private Bitmap mImage;
    private String mText;
    private String mDate;

    public Article(Bitmap image, String text, String date)
    {
        mImage = image;
        mText = text;
        mDate = date;
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
