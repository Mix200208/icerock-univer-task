package com.example.todolistkotlin.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBaseHelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VERSION) {

    companion object{
         const val DATABASE_NAME = "TaskDb.db"
         const val DATABASE_VERSION = 1
         const val TABLE_NAME = "my_taskList"
         const val COLUMN_ID = "_id"
         const val COLUMN_TASK = "task_name"
         const val COLUMN_REVIEW = "task_review"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        val query =  "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY," +
                "$COLUMN_TASK TEXT," +
                "$COLUMN_REVIEW TEXT)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addTask(taskName:String,taskReview:String){
        val db:SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_TASK,taskName)
        cv.put(COLUMN_REVIEW,taskReview)
        db.insert(TABLE_NAME,null,cv)
    }

    fun readAllData():Cursor?{
        val query = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        if(db!=null){
            cursor = db.rawQuery(query,null)
        }
        return cursor
    }

    fun updateData(id:String,taskName:String,taskReview:String){
        val db:SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_TASK,taskName)
        cv.put(COLUMN_REVIEW,taskReview)
        val rows = db.update(TABLE_NAME, cv, "_id = ?", arrayOf(id))

        if (rows!=1){
            Log.e("Res","Good")
        }
    }

    fun deleteData(id:String){
        val db:SQLiteDatabase = this.writableDatabase
        val res = db.delete(TABLE_NAME, "_id = ?", arrayOf(id))

        if (res!=1){
            Log.e("Res","Good")
        }

    }

    fun deleteAllData(){
        val db:SQLiteDatabase = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
    }

}