package com.example.lukas.newsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FavoritesDataSource {
    private Context mContext;

    private FavoritesSQLiteHelper mFavoritesSQLiteHelper;
    public FavoritesDataSource(Context context)
    {
        mContext = context;
        mFavoritesSQLiteHelper = new FavoritesSQLiteHelper(context);
        SQLiteDatabase database = mFavoritesSQLiteHelper.getReadableDatabase();
        database.close();
    }
    private SQLiteDatabase open()
    {
        return mFavoritesSQLiteHelper.getWritableDatabase();
    }
    private void close(SQLiteDatabase db)
    {
        db.close();
    }

   /* public ArrayList<Article> read()
    {
        ArrayList<Article> list = new ArrayList<>();
        SQLiteDatabase
        Cursor cursor =
    }*/
    public void create(Article article)
    {
        SQLiteDatabase db = open();
        db.beginTransaction();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FavoritesSQLiteHelper.TEXT, article.getmText());
        contentValues.put(FavoritesSQLiteHelper.DATE, article.getmDate());
        contentValues.put(FavoritesSQLiteHelper.DESCRIPTION, article.getmDescription());
        contentValues.put(FavoritesSQLiteHelper.URL, article.getmURL());
        contentValues.put(FavoritesSQLiteHelper.IMAGE_URL, article.getmImageUrl());
        db.insert(FavoritesSQLiteHelper.TABLE_NAME, null,contentValues);

        db.setTransactionSuccessful();
        db.endTransaction();

        close(db);
    }
}
