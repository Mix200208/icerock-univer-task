package com.example.todolistkotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.todolistkotlin.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val add_button:ImageButton = findViewById(R.id.createToDoButton)
        add_button.setOnClickListener {

            val intent = Intent(it.context,AddActivity::class.java)
            startActivity(intent)

        }
    }
}