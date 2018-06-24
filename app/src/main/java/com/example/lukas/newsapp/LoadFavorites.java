package com.example.lukas.newsapp;

import android.content.SharedPreferences;

import java.util.List;

public class LoadFavorites extends Thread {

    private DataAdapter dataAdapter;
    private List<Article> list;
    public LoadFavorites(DataAdapter dataAdapter, List<Article> list)
    {
        this.dataAdapter = dataAdapter;
        this.list = list;
    }
    @Override
    public void run() {

        super.run();
    }
}
