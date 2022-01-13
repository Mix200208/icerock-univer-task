package com.example.todolistkotlin.ViewModels

import androidx.lifecycle.ViewModel
import com.example.todolistkotlin.DB.DataBaseHelper

class EditViewModel(val myDb:DataBaseHelper):ViewModel() {
     var task_text: String = ""
     var review_text: String = ""

    fun saveTaskText(text:String){
        task_text = text
    }

    fun saveReviewText(text:String){
        review_text = text
    }


    fun updateData(id:String){
        myDb.updateData(id,review_text,task_text)
    }


}