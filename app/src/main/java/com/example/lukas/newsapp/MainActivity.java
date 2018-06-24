package com.example.lukas.newsapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnArticleClickedListener, OnRefreshFinishedListener
{

    private RecyclerView mRecyclerView;
    private DataAdapter mDataAdapter;
    private List<Article> mList;
    private final String mURL = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=9a6e62a024b942fe859acccc422b137e";
    SwipeRefreshLayout mSwipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bindViews();
        mDataAdapter = new DataAdapter(this,mList, new PicassoImageLoader(),this);
        new Network(mURL,mDataAdapter,this).execute();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mDataAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Network(mURL,mDataAdapter,MainActivity.this).execute();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.favorite:
                Intent intent = new Intent(this, FavoritesActivity.class);
                this.startActivity(intent);

                break;

                    default:
                    return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public void onArticleClicked(Article article) {
        Intent intent = new Intent(this, DetailedActivity.class);
        intent.putExtra("description", article.getmDescription());
        intent.putExtra("url",article.getmURL());
        intent.putExtra("imageUrl", article.getmImageUrl());
        this.startActivity(intent);
    }

    @Override
    public void onArticleLongClicked(Article article) {
        Toast.makeText(this,"LongClick",Toast.LENGTH_LONG).show();
        FavoritesDataSource dataSource = new FavoritesDataSource(this);
        dataSource.getDatabase();
        dataSource.create(article);

    }


    private void bindViews()
    {
        mRecyclerView = findViewById(R.id.list);
        mSwipeRefreshLayout = findViewById(R.id.swiperefresh);
    }

    @Override
    public void onRefreshFinish() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    class bandau extends Handler
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}
