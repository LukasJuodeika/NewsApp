package com.example.lukas.newsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Network extends AsyncTask<AsyncDataObject, Void, DataAdapter>{


    public static String getJSONString(String URL) throws Exception {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        String responseText = response.body().string();

        return responseText;
    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            // ;



            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

    public static List<Article> getArticleFromJSON(String jsonString) throws JSONException
    {
        List<Article> articles = new ArrayList<>();

        JSONObject object = new JSONObject(jsonString);
        JSONArray array = object.getJSONArray("articles");
        String date, text, image, description,url;
        Bitmap bitImage;

        for(int i = 0; i<array.length();i++)//bitmap, String, String
        {
            JSONObject o = array.getJSONObject(i);
            date = o.getString("publishedAt");
            text = o.getString("title");
            image = o.getString("urlToImage");
            description = o.getString("description");
            url = o.getString("url");
            //bitImage = getBitmapFromURL(image);

            Article article = new Article(image, text,date,description,url);
            articles.add(article);
        }
        return articles;
    }

    public static List<Article> getArticles(String URL) {

        String data = null;
        List<Article> articles = null;
        try {
            data = getJSONString(URL);
            articles = getArticleFromJSON(data);
        } catch (Exception e) {
        }
        return articles;
    }



    @Override
    protected DataAdapter doInBackground(AsyncDataObject... asyncDataObjects) {
        List<Article> articles = getArticles(asyncDataObjects[0].getUrl());


        asyncDataObjects[0].adapter.addAll(articles);
        Log.d("dataLoad", articles.get(0).getmDate());
        Log.d("testtt",asyncDataObjects[0].adapter.getItemCount()+"");
        return asyncDataObjects[0].adapter;

    }

    @Override
    protected void onPostExecute(DataAdapter dataAdapter) {
        super.onPostExecute(dataAdapter);
        dataAdapter.notifyDataSetChanged();
        Log.d("Network.java class","finished loading data.............................................................." +
                ".........................................");

    }
}
