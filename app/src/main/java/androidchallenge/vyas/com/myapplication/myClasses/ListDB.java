package androidchallenge.vyas.com.myapplication.myClasses;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ListDB {

  // Database fields
  private SQLiteDatabase database;
  private ListSQLiteHelper dbHelper;
  private String[] allColumns = { ListSQLiteHelper.COLUMN_ID,
      ListSQLiteHelper.COLUMN_TASK_STR,
      ListSQLiteHelper.COLUMN_THEME_COLOR
  };

  public ListDB(Context context) {
    dbHelper = new ListSQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public ListPojo createNewTask(String task_str,int theme_color) {
    ContentValues newClass = new ContentValues();

	newClass.put(ListSQLiteHelper.COLUMN_TASK_STR, task_str);
	newClass.put(ListSQLiteHelper.COLUMN_THEME_COLOR, theme_color);
	
    Log.i("Tryiung", newClass.toString());
    long insertId = database.insert(ListSQLiteHelper.TABLE_NOTES_LIST, null,
        newClass);

    Log.i("Tryiung",""+ insertId);
    Cursor cursor = database.query(ListSQLiteHelper.TABLE_NOTES_LIST,
        allColumns, ListSQLiteHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
    ListPojo newMyClass = cursorToMyClass(cursor);
    cursor.close();
    return newMyClass;
  }

  public void deleteTask(ListPojo task) {
    long id = task.getId();
    System.out.println("Participant deleted with id: " + id);
    database.delete(ListSQLiteHelper.TABLE_NOTES_LIST, ListSQLiteHelper.COLUMN_ID
        + " = " + id, null);
  }

  public List<ListPojo> getAllTasksList() {
    List<ListPojo> allTasks = new ArrayList<ListPojo>();

    Cursor cursor = database.query(ListSQLiteHelper.TABLE_NOTES_LIST,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      ListPojo aClass = cursorToMyClass(cursor);
      allTasks.add(aClass);
      cursor.moveToNext();
    }
    // make sure to close the cursor
    cursor.close();
    return allTasks;
  }

  private ListPojo cursorToMyClass(Cursor cursor) {
    ListPojo taskPojo = new ListPojo();
    taskPojo.setId(cursor.getLong(0));
    taskPojo.setTaskStr(cursor.getString(1));
    taskPojo.setThemeColor(cursor.getInt(2));
   
    return taskPojo;
  }
} 