package com.example.lukas.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements OnArticleClickedListener{

    private RecyclerView mRecyclerView;
    private DataAdapter mDataAdapter;
    private List<Article> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mDataAdapter = new DataAdapter(this,mList,new PicassoImageLoader(),this);
       // bindList
        Bundle bundle = new Bundle();
        Thread thread = new Thread();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mDataAdapter);
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

    }

    @Override
    protected void onResume() {
        super.onResume();

        FavoritesDataSource dataSource = new FavoritesDataSource(this);
    }
}
