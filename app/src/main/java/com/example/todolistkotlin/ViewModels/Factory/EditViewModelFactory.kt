package com.example.todolistkotlin.ViewModels.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolistkotlin.DB.DataBaseHelper
import com.example.todolistkotlin.ViewModels.EditViewModel
import com.example.todolistkotlin.ViewModels.MainViewModel

class EditViewModelFactory(private val myDb: DataBaseHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditViewModel(myDb) as T
    }

}