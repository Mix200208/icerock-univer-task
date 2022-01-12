package com.example.todolistkotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.todolistkotlin.R
import com.example.todolistkotlin.model.Task

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val task = intent.getParcelableExtra<Task>(MainActivity.INTENT_PARCELABLE)

        if (task!=null){
            findViewById<EditText>(R.id.change_task_name).apply {
                setText(task.task)
            }
             findViewById<EditText>(R.id.change_task_review).apply {
                 setText(task.review)
             }

        }






    }
}