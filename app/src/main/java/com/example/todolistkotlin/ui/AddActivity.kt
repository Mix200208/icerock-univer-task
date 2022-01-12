package com.example.todolistkotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import com.example.todolistkotlin.DB.DataBaseHelper
import com.example.todolistkotlin.R

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val but = findViewById<ImageButton>(R.id.addTaskButton)
        val task_input = findViewById<EditText>(R.id.input_task_name)
        val review_input  = findViewById<EditText>(R.id.input_task_review)

        but.setOnClickListener {
            val myDb  = DataBaseHelper(it.context)
            myDb.addTask(task_input.text.toString(),review_input.text.toString())
        }



    }
}