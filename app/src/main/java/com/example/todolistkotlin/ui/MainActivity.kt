package com.example.todolistkotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistkotlin.DB.DataBaseHelper
import com.example.todolistkotlin.R
import com.example.todolistkotlin.adapter.ListTaskAdapter
import com.example.todolistkotlin.model.Task

class MainActivity : AppCompatActivity() {

    private val listTask:MutableList<Task> = mutableListOf()
    private val myDb  = DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv:RecyclerView = findViewById(R.id.task_rv)
        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)
        val taskAdapter = ListTaskAdapter({},{})
        addAllData()
        taskAdapter.setData(listTask)
        rv.adapter = taskAdapter
        val add_button:ImageButton = findViewById(R.id.createToDoButton)
        add_button.setOnClickListener {
            val intent = Intent(it.context,AddActivity::class.java)
            startActivity(intent)
        }




    }



    private fun addAllData(){

        val cursor = myDb.readAllData()
        if (cursor?.count==0){
            Log.e("Answer"," DB empty")
        }else{
            while (cursor?.moveToNext()!!){
                listTask.add(Task(cursor.getString(1),cursor.getString(2)))
            }
        }
    }



}