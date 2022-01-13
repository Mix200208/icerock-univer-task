package com.example.todolistkotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.todolistkotlin.DB.DataBaseHelper
import com.example.todolistkotlin.R
import com.example.todolistkotlin.ViewModels.EditViewModel
import com.example.todolistkotlin.ViewModels.Factory.EditViewModelFactory
import com.example.todolistkotlin.ViewModels.Factory.MainViewModelFactory
import com.example.todolistkotlin.ViewModels.MainViewModel
import com.example.todolistkotlin.databinding.ActivityEditBinding
import com.example.todolistkotlin.model.Task

class EditActivity : AppCompatActivity() {
    lateinit var editViewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit)
        val myDb  = DataBaseHelper(this)
        val task = intent.getParcelableExtra<Task>(MainActivity.INTENT_PARCELABLE)
        editViewModel = ViewModelProvider(this, EditViewModelFactory(myDb)).get(EditViewModel::class.java)
        editViewModel.saveTaskText(task?.task!!)
        editViewModel.saveReviewText(task.review!!)

        val changeTask  = binding.changeTaskName.apply {

            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {

                }

                override fun onTextChanged(
                    s: CharSequence?, start: Int, before: Int, count: Int
                ) {
                }

                override fun afterTextChanged(s: Editable?) {
                    editViewModel.saveTaskText(s.toString())
                }

            })
        }

        val review = binding.changeTaskReview.apply {

                addTextChangedListener( object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int
                    ) {

                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int
                    ) {
                    }

                    override fun afterTextChanged(s: Editable?) {
                        editViewModel.saveReviewText(s.toString())
                    }

                })
        }

        binding.editTask.setOnClickListener {
                editViewModel.updateData(task.id.toString())
                Intent(it.context,MainActivity::class.java).apply {
                    startActivity(this)
                }
            }

        }








}
