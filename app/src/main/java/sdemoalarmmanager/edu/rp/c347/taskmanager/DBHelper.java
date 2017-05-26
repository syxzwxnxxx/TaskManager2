package sdemoalarmmanager.edu.rp.c347.taskmanager;

/**
 * Created by 15017466 on 26/5/2017.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.EventLogTags;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Task.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TASK = "TaskManager";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_TASK_NAME = "Task_Name";
    private static final String COLUMN_DESC = "Description";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE Note
        // (id INTEGER PRIMARY KEY AUTOINCREMENT, note_content TEXT, rating
        // INTEGER );
        String createNoteTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TASK_NAME + " TEXT, "
                + COLUMN_DESC + " TEXT )";
        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");

        //Dummy records, to be inserted when the database is created
        for (int i = 0; i< 4; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TASK_NAME, "Data number " + i);
            db.insert(TABLE_TASK, null, values);
        }
        Log.i("info", "dummy records inserted");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("ALTER TABLE " + TABLE_TASK + " ADD COLUMN module_name TEXT");
    }

    public long insertTask(String taskContent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK_NAME, taskContent);
        long result = db.insert(TABLE_TASK, null, values);
        db.close();
        Log.d("SQL Insert",""+ result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Task> getAllTask() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        // "SELECT id, note_content, stars FROM note"
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TASK_NAME + ","
                + COLUMN_DESC
                + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String task_name = cursor.getString(1);
                String description = cursor.getString(2);

                Task task = new Task(id, task_name, description);
                tasks.add(task);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return tasks;
    }

    public ArrayList<String> getTask() {
        //TODO return records in Strings

        // Create an ArrayList that holds String objects
        ArrayList<String> notes = new ArrayList<String>();
        // Select all the notes' content
        String selectQuery = "SELECT " + COLUMN_TASK_NAME + " FROM "
                + TABLE_TASK;

        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);
        // moveToFirst() moves to first row
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row and returns true;
            // moveToNext() returns false when no more next row to move to
            do {
                // Add the note content to the ArrayList object
                notes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return notes;
    }

    public int updateTask(Task data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK_NAME, data.getTaskName());
        values.put(COLUMN_DESC, data.getDescription());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_TASK, values, condition, args);
        db.close();
        return result;
    }


}

