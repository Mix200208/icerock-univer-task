package com.example.todolistkotlin.di

import android.content.Context
import com.example.todolistkotlin.DB.DataBaseHelper

class DataBaseHelperFactory(private val context: Context){

    companion object{
        var dB: DataBaseHelper? = null
    }


    fun getInstance():DataBaseHelper?{
        return if (dB==null){
            dB = DataBaseHelper(context = context)
            dB
        }else{
            dB
        }
    }

}