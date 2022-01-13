package com.example.todolistkotlin.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.todolistkotlin.DB.DataBaseHelper
import com.example.todolistkotlin.model.Task

class MainViewModel(private val myDb:DataBaseHelper):ViewModel() {
    val listTask:MutableList<Task> = mutableListOf()

    fun deleteData(id:String){
        myDb.deleteData(id)
    }

    fun deleteAllData(){
        myDb.deleteAllData()
    }


    fun addAllData(){

        val cursor = myDb.readAllData()
        if (cursor?.count==0){
            Log.e("Answer"," DB is empty")
        }else{
            while (cursor?.moveToNext()!!){
                listTask.add(
                    Task(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )
            }
        }
    }





}