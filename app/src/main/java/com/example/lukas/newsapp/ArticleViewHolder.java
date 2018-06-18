package com.example.lukas.newsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ArticleViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
    private TextView mText;
    private TextView mDate;
    private ImageView mImageView;
    private Context mContext;

    public ArticleViewHolder(Context context, View itemView) {
        super(itemView);
        mText = (TextView) itemView.findViewById(R.id.text_field1);
        mDate = (TextView) itemView.findViewById(R.id.text_field2);
        mImageView = (ImageView) itemView.findViewById(R.id.list_image);
        mContext = context;
        itemView.setOnClickListener(this);
    }

    public void bindView(Article article) {
        mText.setText(article.getmText());
        mDate.setText(article.getmDate());
        mImageView.setImageBitmap(article.getmImage());
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(mContext,"hello",Toast.LENGTH_LONG).show();
    }
}
