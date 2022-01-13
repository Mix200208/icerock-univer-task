package com.example.todolistkotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.todolistkotlin.DB.DataBaseHelper
import com.example.todolistkotlin.R
import com.example.todolistkotlin.viewmodels.AddViewModel
import com.example.todolistkotlin.viewmodels.factory.AddViewModelFactory
import com.example.todolistkotlin.databinding.ActivityAddBinding
import com.example.todolistkotlin.di.DataBaseHelperFactory

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding:ActivityAddBinding = DataBindingUtil.setContentView(this, R.layout.activity_add)
        val addViewModel = ViewModelProvider(this, AddViewModelFactory(DataBaseHelperFactory.dB!!)).get(AddViewModel::class.java)

        binding.addTaskButton.apply {
            setOnClickListener {
                val isValidated = addViewModel.validateData(
                    binding.inputTaskName.text.toString(),
                    binding.inputTaskReview.text.toString()
                )

                if (isValidated) {
                    Toast.makeText(this@AddActivity,"Заполните оба поля",Toast.LENGTH_SHORT).show()
                }else {
                    addViewModel.addData(
                        binding.inputTaskName.text.toString(),
                        binding.inputTaskReview.text.toString()
                    )
                    Intent(this.context, MainActivity::class.java).apply {
                        startActivity(this)
                    }
                }
            }
        }

    }
}