package com.example.lukas.newsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArticleViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
    private TextView mText;
    private TextView mDate;
    private ImageView mImageView;
    private Context mContext;
    private Article mArticle;

    public ArticleViewHolder(Context context, View itemView) {
        super(itemView);
        mText = (TextView) itemView.findViewById(R.id.text_field1);
        mDate = (TextView) itemView.findViewById(R.id.text_field2);
        mImageView = (ImageView) itemView.findViewById(R.id.list_image);
        mContext = context;
        itemView.setOnClickListener(this);
    }

    public void bindView(Article article) {
        mArticle = article;
        mText.setText(article.getmText());
        mDate.setText(article.getmDate());
        mImageView.setImageBitmap(article.getmImage());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, DetailedActivity.class);

        intent.putExtra("description", mArticle.getmDescription());
        intent.putExtra("url",mArticle.getmURL());
      //  saveBitmap(mArticle.getmLargeImage());
        saveImageToInternalStorage(mArticle.getmLargeImage());
        mContext.startActivity(intent);
    }


    public boolean saveImageToInternalStorage(Bitmap image) {

        try {
            FileOutputStream fos = mContext.openFileOutput("current_image.png", Context.MODE_PRIVATE);
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
            return true;
        } catch (Exception e) {
            Log.e("saveToInternalStorage()", e.getMessage());
            return false;
        }
    }
   /* public void saveBitmap(Bitmap bitmap)
    {


        FileOutputStream out = null;
        try {
            out = new FileOutputStream("current_image");
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }*/


}
