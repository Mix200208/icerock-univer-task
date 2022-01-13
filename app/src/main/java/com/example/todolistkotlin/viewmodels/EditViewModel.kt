package com.example.todolistkotlin.viewmodels

import androidx.lifecycle.ViewModel
import com.example.todolistkotlin.DB.DataBaseHelper

class EditViewModel(val myDb:DataBaseHelper):ViewModel() {


    fun updateTask(id:String,task_text:String,review_text:String){
        myDb.updateData(id,task_text,review_text)
    }

    fun validateChangedTask(taskText:String,reviewText:String):Boolean{
        return  ((taskText.isEmpty())||reviewText.isEmpty())
    }


}