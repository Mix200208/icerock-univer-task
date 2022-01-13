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
import com.example.todolistkotlin.ViewModels.AddViewModel
import com.example.todolistkotlin.ViewModels.EditViewModel
import com.example.todolistkotlin.ViewModels.Factory.AddViewModelFactory
import com.example.todolistkotlin.ViewModels.Factory.EditViewModelFactory
import com.example.todolistkotlin.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myDb  = DataBaseHelper(this)
        val binding:ActivityAddBinding = DataBindingUtil.setContentView(this, R.layout.activity_add)
        val addViewModel = ViewModelProvider(this, AddViewModelFactory(myDb)).get(AddViewModel::class.java)

        val task_input = binding.inputTaskName.apply {
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
                    addViewModel.task_text = s.toString()
                }

            })
        }

        val review_input  = binding.inputTaskReview.apply{
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
                    addViewModel.review_text = s.toString()
                }

            })

        }

        binding.addTaskButton.apply {
            setOnClickListener {

                addViewModel.addData()
                Intent(this.context,MainActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }




    }
}