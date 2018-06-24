package com.example.lukas.newsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailedActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String description = extras != null ? extras.getString("description") : "";
        final String url = extras.getString("url");
        String imageUrl =  extras.getString("imageUrl");

        setUpViews();

        textView.setText(description);
        new PicassoImageLoader().load(imageUrl, imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });



    }

    private void setUpViews() {
        textView = (TextView) findViewById(R.id.description);
        button = (Button) findViewById(R.id.read_article_button);
        imageView = (ImageView) findViewById(R.id.image);
    }


}
