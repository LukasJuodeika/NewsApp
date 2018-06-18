package com.example.lukas.newsapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleViewHolder extends RecyclerView.ViewHolder {
    private TextView mText;
    private TextView mDate;
    private ImageView mImageView;

    public ArticleViewHolder(View itemView) {
        super(itemView);
        mText = (TextView) itemView.findViewById(R.id.text_field1);
        mDate = (TextView) itemView.findViewById(R.id.text_field2);
        mImageView = (ImageView) itemView.findViewById(R.id.list_image);
    }

    public void bindView(Article article) {
        mText.setText(article.getmText());
        mDate.setText(article.getmDate());
        mImageView.setImageBitmap(article.getmImage());
    }
}
