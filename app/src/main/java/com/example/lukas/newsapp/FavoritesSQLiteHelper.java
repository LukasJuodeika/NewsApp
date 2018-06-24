package com.example.lukas.newsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavoritesSQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "favorites.db";
    private static final int DB_VERSION = 1;
    public static final String TEXT = "M_TEXT";
    public static final String DATE = "DATE";
    public static final String DESCRIPTION= "DESCRIPTION";
    public static final String URL = "URL";
    public static final String IMAGE_URL = "IMAGE_URL";
    public static final String TABLE_NAME = "SAVED_ARTICLES";


    private static String CREATE_FAVORITES =
            "CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    TEXT+ " TEXT,"+
                    DATE +" TEXT,"+
                    DESCRIPTION +" TEXT,"+
                    URL +" TEXT,"+
                    IMAGE_URL +" TEXT)";

    public FavoritesSQLiteHelper(Context context)
    {
        super(context,DB_NAME, null, DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FAVORITES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
