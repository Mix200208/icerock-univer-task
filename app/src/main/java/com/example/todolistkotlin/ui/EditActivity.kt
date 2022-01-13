package com.example.todolistkotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.todolistkotlin.DB.DataBaseHelper
import com.example.todolistkotlin.R
import com.example.todolistkotlin.viewmodels.EditViewModel
import com.example.todolistkotlin.viewmodels.factory.EditViewModelFactory
import com.example.todolistkotlin.databinding.ActivityEditBinding
import com.example.todolistkotlin.di.DataBaseHelperFactory
import com.example.todolistkotlin.model.Task

class EditActivity : AppCompatActivity() {
    lateinit var editViewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit)
        val task = intent.getParcelableExtra<Task>(MainActivity.INTENT_PARCELABLE)
        editViewModel = ViewModelProvider(this, EditViewModelFactory(DataBaseHelperFactory.dB!!)).get(EditViewModel::class.java)
        binding.changeTaskName.setText(task?.task!!)
        binding.changeTaskReview.setText(task.review)

        binding.editTask.setOnClickListener {
            val isValidated = editViewModel.validateChangedTask(
                binding.changeTaskName.text.toString(),
                binding.changeTaskReview.text.toString()
            )
            if (isValidated){
                Toast.makeText(this,"Оба поля должны быть заполнены", Toast.LENGTH_SHORT).show()

            }else{
                editViewModel.updateTask(
                    task.id.toString(),
                    binding.changeTaskName.text.toString(),
                    binding.changeTaskReview.text.toString()
                )

                Intent(it.context,MainActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }

        }








}
