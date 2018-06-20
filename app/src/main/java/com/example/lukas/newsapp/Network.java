package com.example.lukas.newsapp;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Network extends AsyncTask<Void, Void, Void>{


    private String mUrl;
    private DataAdapter mAdapter;
    private OnRefreshFinishedListener mOnRefreshFinishedListener;
    public Network(String url, DataAdapter adapter, OnRefreshFinishedListener listener)
    {
        mUrl = url;
        mAdapter = adapter;
        mOnRefreshFinishedListener = listener;
    }

    private static String getJSONString(String URL) throws Exception {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        return response.body().string();
    }

    private static List<Article> getArticleFromJSON(String jsonString) throws JSONException
    {
        List<Article> articles = new ArrayList<>();

        JSONObject object = new JSONObject(jsonString);
        JSONArray array = object.getJSONArray("articles");
        String date, text, image, description,url;

        for(int i = 0; i<array.length();i++)//bitmap, String, String
        {
            JSONObject o = array.getJSONObject(i);
            date = o.getString("publishedAt");
            text = o.getString("title");
            image = o.getString("urlToImage");
            description = o.getString("description");
            url = o.getString("url");

            if(description != null && description != "null") {
                Article article = new Article(image, text, date, description, url);
                articles.add(article);
            }
        }
        return articles;
    }

    private static List<Article> getArticles(String URL) {
        String data;
        List<Article> articles = null;
        try {
            data = getJSONString(URL);
            articles = getArticleFromJSON(data);
        } catch (Exception ignored) {
        }
        return articles;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mAdapter.notifyDataSetChanged();
        mOnRefreshFinishedListener.onRefreshFinish();
    }


    @Override
    protected Void doInBackground(Void... voids) {
        List<Article> articles = getArticles(mUrl);
        mAdapter.addAll(articles);
        return null;
    }
}
