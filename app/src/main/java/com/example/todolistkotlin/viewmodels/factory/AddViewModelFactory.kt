package com.example.todolistkotlin.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolistkotlin.DB.DataBaseHelper
import com.example.todolistkotlin.viewmodels.AddViewModel


class AddViewModelFactory(private val myDb: DataBaseHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddViewModel(myDb) as T
    }
}