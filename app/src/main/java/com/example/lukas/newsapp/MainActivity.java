package com.example.lukas.newsapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DataAdapter mDataAdapter;
    private List<Article> mList;
    private final String mURL = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=9a6e62a024b942fe859acccc422b137e";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.list);

        mList = Network.getArticles(mURL);
        mDataAdapter = new DataAdapter(mList);
        new Network().execute(new AsyncDataObject(mURL,mDataAdapter));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mDataAdapter);

        



    }

}
