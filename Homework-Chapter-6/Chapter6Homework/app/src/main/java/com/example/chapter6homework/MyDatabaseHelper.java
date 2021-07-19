package com.example.chapter6homework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String IS_FINISHED = "isFinished";
    public static final String WHAT_TO_DO = "whatToDo";
    public static final String CREATE_TIME = "createTime";
    public static final String PRIORITY = "priority";

    private static final String CREATE_LIST =
            "create table List("
            + "isFinished integer, "
            + "whatToDo text, "
            + "createTime text,"
            + "priority integer)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory,
                            int version){
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
