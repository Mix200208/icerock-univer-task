package com.example.todolistkotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistkotlin.DB.DataBaseHelper
import com.example.todolistkotlin.R
import com.example.todolistkotlin.ui.adapter.ListTaskAdapter
import com.example.todolistkotlin.model.Task

class MainActivity : AppCompatActivity() {

    private val listTask:MutableList<Task> = mutableListOf()
    private val myDb  = DataBaseHelper(this)

    companion object {
        const val INTENT_PARCELABLE = "OBJECT_INTENT"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val taskAdapter = ListTaskAdapter({ val intent = Intent(this, EditActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)}, {myDb.deleteData(it.id.toString())
            this.listTask.remove(it)}).apply {
            addAllData()
            setData(listTask)
        }


       findViewById<RecyclerView>(R.id.task_rv).apply {

            setHasFixedSize(true)
            taskAdapter.setData(listTask)
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(this.context)

        }


        findViewById<ImageButton>(R.id.createToDoButton).apply {

            setOnClickListener {
                Intent(this.context,AddActivity::class.java).apply {
                    startActivity(this)
                }
            }


        }

        findViewById<Button>(R.id.updateButton).apply{
            setOnClickListener {
                listTask.clear()
                addAllData()
                taskAdapter.setData(listTask)
            }


        }





    }



    private fun addAllData(){

        val cursor = myDb.readAllData()
        if (cursor?.count==0){
            Log.e("Answer"," DB empty")
        }else{
            while (cursor?.moveToNext()!!){
                listTask.add(Task(cursor.getInt(0),cursor.getString(1),cursor.getString(2)))
            }
        }
    }



}