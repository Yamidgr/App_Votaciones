package com.example.votaciones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String TBL_CANDIDATOS = "CREATE TABLE tbl_candidatos(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, " +
            "votes int)";

    private static final String TBL_PERSONAS = "CREATE TABLE tbl_personas(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, " +
            "document int, " +
            "date int, " +
            "age int)";

    private static final String DB_NAME = "DB_VOTACIONES";
    private static final int DB_VERSION = 1;

    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TBL_CANDIDATOS);
        db.execSQL(TBL_PERSONAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
