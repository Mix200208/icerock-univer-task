package com.example.todolistkotlin.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolistkotlin.DB.DataBaseHelper
import com.example.todolistkotlin.viewmodels.MainViewModel

class MainViewModelFactory(private val myDb:DataBaseHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(myDb) as T
    }
}