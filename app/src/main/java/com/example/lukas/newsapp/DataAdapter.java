package com.example.lukas.newsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
    private List<Article> mList;

    public DataAdapter(List<Article> list)
    {
        mList = list;
    }
    public void addAll(List<Article> list)
    {
        mList = list;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,
                parent, false);
        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Article article = mList.get((position));
        holder.bindView(article);

    }

    @Override
    public int getItemCount() {
        if(mList == null)
            return 0;
        return mList.size();
    }
}
