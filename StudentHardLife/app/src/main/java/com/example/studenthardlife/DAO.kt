package com.example.studenthardlife

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DAO(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "tasksDB.db"
        private const val TABLE_TASKS = "Tasks"

        private const val COLUMN_ID = "_id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_SUBJECT = "subject"
        private const val COLUMN_DESCRIPTION = "description"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TASKS_TABLE =
            "CREATE TABLE $TABLE_TASKS( " +
            "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "$COLUMN_TITLE TEXT," +
            "$COLUMN_SUBJECT TEXT," +
            "$COLUMN_DESCRIPTION TEXT)"
        db?.execSQL(CREATE_TASKS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_TASKS")
        onCreate(db)
    }

    fun addTask(task: Task) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_TITLE, task.title)
        contentValues.put(COLUMN_SUBJECT, task.subject)
        contentValues.put(COLUMN_DESCRIPTION, task.description)

        db.insert(TABLE_TASKS, null, contentValues)
        db.close()
    }

    fun deleteTask(taskId: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_TASKS, "$COLUMN_ID=${taskId}", null)
        db.close()
    }

    fun updateTask(id: Int, title: String, subject: String, description: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_TITLE, title)
        contentValues.put(COLUMN_SUBJECT, subject)
        contentValues.put(COLUMN_DESCRIPTION, description)

        db.update(TABLE_TASKS, contentValues, "$COLUMN_ID=$id", null)
    }

    fun getTasks(): List<Task> {
        val tasks: MutableList<Task> = ArrayList()
        val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM $TABLE_TASKS", null)
        if (cursor.moveToFirst()) {
            do {
                tasks.add(Task(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)))
            } while (cursor.moveToNext())
        }
        db.close()
        cursor.close()
        return tasks
    }
}