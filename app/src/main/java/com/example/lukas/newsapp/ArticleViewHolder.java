package com.example.lukas.newsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class ArticleViewHolder extends RecyclerView.ViewHolder{
    private TextView mText;
    private TextView mDate;
    private ImageView mImageView;
    private Article mArticle;
    private ImageLoader imageLoader;
    private OnArticleClickedListener articleClickListener;

    public ArticleViewHolder(Context context,
                             View itemView,
                             ImageLoader imageLoader,
                             OnArticleClickedListener listener) {
        super(itemView);
        this.imageLoader = imageLoader;
        articleClickListener = listener;
        mText = itemView.findViewById(R.id.text_field1);
        mDate = itemView.findViewById(R.id.text_field2);
        mImageView = itemView.findViewById(R.id.list_image);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleClickListener.onArticleClicked(mArticle);
            }
        });
    }

    public void bindView(Article article) {
        mArticle = article;
        imageLoader.loadThumbnail(article.getmImageUrl(), mImageView);
        mText.setText(article.getmText());
        mDate.setText(article.getmDate());
    }

}
