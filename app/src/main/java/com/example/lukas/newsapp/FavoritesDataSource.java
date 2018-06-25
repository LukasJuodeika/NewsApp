package com.example.lukas.newsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

public class FavoritesDataSource{
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
    public ArrayList<Article> getDatabase() {


        String selectQuery = "SELECT  * FROM " + FavoritesSQLiteHelper.TABLE_NAME;
        SQLiteDatabase db  =  mFavoritesSQLiteHelper.getReadableDatabase();
        Cursor cursor      = db.query(FavoritesSQLiteHelper.TABLE_NAME, null,
                null, null, null, null, null);
        ArrayList<Article>  data =  new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                // get the data into array, or class variable
                Article a = new Article(
                        cursor.getString(5),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
                data.add(a);
              //  Log.d("dbLoader", cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public void deleteAllData()
    {
        SQLiteDatabase db = open();
        db.delete(FavoritesSQLiteHelper.TABLE_NAME,null,null);
    }

}
