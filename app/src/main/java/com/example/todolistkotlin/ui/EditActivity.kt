package com.example.todolistkotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import com.example.todolistkotlin.DB.DataBaseHelper
import com.example.todolistkotlin.R
import com.example.todolistkotlin.model.Task

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val task = intent.getParcelableExtra<Task>(MainActivity.INTENT_PARCELABLE)

        if (task!=null){
            val chageTask  = findViewById<EditText>(R.id.change_task_name).apply {
                setText(task.task)
            }
            val review = findViewById<EditText>(R.id.change_task_review).apply {
                 setText(task.review)
             }

            findViewById<ImageButton>(R.id.editTask).setOnClickListener {
                val myDb  = DataBaseHelper(it.context)
                myDb.updateData(task.id.toString(),chageTask.text.toString(),review.text.toString())
                Intent(it.context,MainActivity::class.java).apply {
                    startActivity(this)
                }
            }

        }








    }
}