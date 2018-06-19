package com.example.lukas.newsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
    private TextView mText;
    private TextView mDate;
    private ImageView mImageView;
    private Context mContext;
    private Article mArticle;
    private ImageLoader imageLoader;

    public ArticleViewHolder(Context context, View itemView, ImageLoader imageLoader) {
        super(itemView);
        this.imageLoader = imageLoader;
        mText = itemView.findViewById(R.id.text_field1);
        mDate = itemView.findViewById(R.id.text_field2);
        mImageView = itemView.findViewById(R.id.list_image);
        mContext = context;
        itemView.setOnClickListener(this);
    }

    public void bindView(Article article) {
        mArticle = article;
        imageLoader.loadThumbnail(article.getmImageUrl(), mImageView);

        mText.setText(article.getmText());
        mDate.setText(article.getmDate());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, DetailedActivity.class);
        intent.putExtra("description", mArticle.getmDescription());
        intent.putExtra("url",mArticle.getmURL());
        intent.putExtra("imageUrl", mArticle.getmImageUrl());
        mContext.startActivity(intent);
    }
}
