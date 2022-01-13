package com.example.todolistkotlin.ui.application

import android.app.Application
import com.example.todolistkotlin.DB.DataBaseHelper
import com.example.todolistkotlin.di.DataBaseHelperFactory

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        DataBaseHelperFactory(this).getInstance()

    }




}