package com.vyas.myClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ListSQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_NOTES_LIST = "notes_list";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_TASK_STR = "task_str";
  public static final String COLUMN_THEME_COLOR = "theme_color";
  
  private static final String DATABASE_NAME = "easyregister.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_NOTES_LIST + "(" + COLUMN_ID  + " integer primary key autoincrement, " 
	      + COLUMN_TASK_STR +  " text not null, "
	      + COLUMN_THEME_COLOR + " text);";
  public ListSQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {

Log.i(ListSQLiteHelper.class.getName(), DATABASE_CREATE);
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(ListSQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES_LIST);
    onCreate(db);
  }

} 