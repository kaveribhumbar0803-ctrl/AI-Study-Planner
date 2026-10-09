package com.example.aistudyplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StudyPlanner.db";
    private static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE subjects (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT NOT NULL, " +
                        "hours TEXT NOT NULL)"
        );

        db.execSQL(
                "CREATE TABLE tasks (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "task_name TEXT NOT NULL, " +
                        "subject TEXT NOT NULL, " +
                        "hours TEXT NOT NULL, " +
                        "priority TEXT NOT NULL, " +
                        "deadline TEXT NOT NULL, " +
                        "completed INTEGER DEFAULT 0)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS subjects");
        db.execSQL("DROP TABLE IF EXISTS tasks");

        onCreate(db);
    }

    public boolean addSubject(String name, String hours) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("hours", hours);

        return db.insert("subjects", null, values) != -1;
    }

    public boolean addTask(
            String taskName,
            String subject,
            String hours,
            String priority,
            String deadline) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("task_name", taskName);
        values.put("subject", subject);
        values.put("hours", hours);
        values.put("priority", priority);
        values.put("deadline", deadline);
        values.put("completed", 0);

        return db.insert("tasks", null, values) != -1;
    }

    public boolean updateTask(
            int id,
            String taskName,
            String subject,
            String hours,
            String priority,
            String deadline) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("task_name", taskName);
        values.put("subject", subject);
        values.put("hours", hours);
        values.put("priority", priority);
        values.put("deadline", deadline);

        int result = db.update(
                "tasks",
                values,
                "id = ?",
                new String[]{String.valueOf(id)}
        );

        return result > 0;
    }

    public boolean markTaskCompleted(int id) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("completed", 1);

        int result = db.update(
                "tasks",
                values,
                "id = ?",
                new String[]{String.valueOf(id)}
        );

        return result > 0;
    }

    public boolean deleteTask(int id) {

        SQLiteDatabase db = getWritableDatabase();

        int result = db.delete(
                "tasks",
                "id = ?",
                new String[]{String.valueOf(id)}
        );

        return result > 0;
    }
}