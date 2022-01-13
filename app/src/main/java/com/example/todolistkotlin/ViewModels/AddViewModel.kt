package com.example.todolistkotlin.ViewModels

import androidx.lifecycle.ViewModel
import com.example.todolistkotlin.DB.DataBaseHelper

class AddViewModel(val myDb: DataBaseHelper):ViewModel() {
    var task_text: String = ""
    var review_text: String = ""

    fun addData(){
        myDb.addTask(review_text,task_text)
    }


}