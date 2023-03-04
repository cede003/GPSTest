package com.example.trackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "coursedb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "locations";
    private static final String ID_COL = "id";
    private static final String LATTITUDE_COL = "lattitude";
    private static final String LONGITUDE_COL = "longitude";

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LATTITUDE_COL + " DOUBLE,"
                + LONGITUDE_COL + " DOUBLE)";

        db.execSQL(query);
    }

    public void addLocation(Double lat, Double lon) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LATTITUDE_COL, lat);
        values.put(LONGITUDE_COL, lon);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}