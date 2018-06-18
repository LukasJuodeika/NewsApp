package com.example.lukas.newsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

      //  Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        String description = extras.getString("description");
        final String url = extras.getString("url");


        textView = (TextView) findViewById(R.id.description);
        button = (Button) findViewById(R.id.read_article_button);
        imageView = (ImageView) findViewById(R.id.image);

        textView.setText(description);
        ///data/user/0/com.example.lukas.newsapp/files/current_image.PNG
        ///storage/emulated/0/com.example.lukas.newsapp/files
       // imageView.setImageBitmap(bitmap);
        //imageView.setImageURI(getFileUri());
       // imageView.setImageURI();
        imageView.setImageBitmap(getThumbnail("current_image.png"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });




    }
    public boolean isSdReadable() {

        boolean mExternalStorageAvailable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageAvailable = true;
            Log.i("isSdReadable", "External storage card is readable.");
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            Log.i("isSdReadable", "External storage card is readable.");
            mExternalStorageAvailable = true;
        } else {
            // Something else is wrong. It may be one of many other
            // states, but all we need to know is we can neither read nor write
            mExternalStorageAvailable = false;
        }

        return mExternalStorageAvailable;
    }


    public Bitmap getThumbnail(String filename) {

        String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath();// + APP_PATH_SD_CARD + APP_THUMBNAIL_PATH_SD_CARD;
        Bitmap thumbnail = null;

// Look for the file on the external storage
        try {
            if (isSdReadable() == true) {
                thumbnail = BitmapFactory.decodeFile(fullPath + "/" + filename);
            }
        } catch (Exception e) {
            Log.e("getThumbnail() extst", e.getMessage());
        }

// If no file on external storage, look in internal storage
        if (thumbnail == null) {
            try {
                File filePath = this.getFileStreamPath(filename);
                FileInputStream fi = new FileInputStream(filePath);
                thumbnail = BitmapFactory.decodeStream(fi);
            } catch (Exception ex) {
                Log.e("getThumbnail() instor", ex.getMessage());
            }
        }
        return thumbnail;
    }



   /* private Uri getFileUri(){

        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
              //  + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/files");


        if (! mediaStorageDir.exists()){

            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        File mediaFile;
        String mImageName="current_image.png";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);

        return Uri.fromFile(mediaFile);
    }
*/


}
