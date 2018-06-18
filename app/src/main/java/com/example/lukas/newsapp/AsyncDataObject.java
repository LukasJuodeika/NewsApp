package com.example.lukas.newsapp;

public class AsyncDataObject {
    private String url;
    public DataAdapter adapter;

    public String getUrl() {
        return url;
    }

    public DataAdapter getAdapter() {
        return adapter;
    }

    public AsyncDataObject(String url, DataAdapter adapter)
    {
        this.url = url;
        this.adapter = adapter;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAdapter(DataAdapter adapter) {
        this.adapter = adapter;
    }
}
