package com.example.snapply.notebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by luweiling on 2016/8/17 0017.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String create_database = "create table data (" +
            "id integer primary key autoincrement," +
            "title text," +
            "content text)";

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        switch (i1) {
            case 1:
                sqLiteDatabase.execSQL(create_database);
            default:
        }
    }
}
