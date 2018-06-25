package com.example.lukas.newsapp;

import android.content.CursorLoader;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements OnArticleClickedListener{

    private RecyclerView mRecyclerView;
    private DataAdapter mDataAdapter;
    private List<Article> mList;
    private FavoritesDataSource mFavoritesDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mDataAdapter = new DataAdapter(this,mList,new PicassoImageLoader(),this);

        mFavoritesDataSource = new FavoritesDataSource(this);
        readData();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mDataAdapter);
    }

    public void readData()
    {
        mList = mFavoritesDataSource.getDatabase();
        mDataAdapter.addAll(mList);
        mDataAdapter.notifyDataSetChanged();
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

        setTitle("Favorites");
        //FavoritesDataSource dataSource = new FavoritesDataSource(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.delete://///////////////////////////////////////
                mFavoritesDataSource.deleteAllData();
                readData();

                break;/////////////////////////////////////////////////
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
