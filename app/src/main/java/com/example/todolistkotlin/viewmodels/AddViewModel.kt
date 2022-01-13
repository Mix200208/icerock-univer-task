package com.example.todolistkotlin.viewmodels

import androidx.lifecycle.ViewModel
import com.example.todolistkotlin.DB.DataBaseHelper

class AddViewModel(private val myDb: DataBaseHelper): ViewModel() {


    fun validateData(taskText:String,reviewText:String):Boolean{
        return  (taskText==""||reviewText=="")
    }
    fun addData(taskText:String,reviewText:String){
        myDb.addTask(taskText,reviewText)
    }


}