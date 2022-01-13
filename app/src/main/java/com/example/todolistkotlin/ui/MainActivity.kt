package com.example.todolistkotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistkotlin.DB.DataBaseHelper
import com.example.todolistkotlin.R
import com.example.todolistkotlin.ViewModels.Factory.MainViewModelFactory
import com.example.todolistkotlin.ViewModels.MainViewModel
import com.example.todolistkotlin.databinding.ActivityMainBinding
import com.example.todolistkotlin.ui.adapter.ListTaskAdapter
import com.example.todolistkotlin.model.Task

class MainActivity : AppCompatActivity() {

    private val listTask:MutableList<Task> = mutableListOf()
    private var taskAdapter: ListTaskAdapter? = null
    lateinit var mainViewModel:MainViewModel
    private val myDb  = DataBaseHelper(this)
    val binding:ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    companion object {
        const val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(myDb)).get(MainViewModel::class.java)
        setUpAdapter()
        setUpUi()

    }

    private fun setUpUi() {
        binding.taskRv.apply {

            setHasFixedSize(true)
            taskAdapter?.setData(mainViewModel.listTask)
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(this.context)

        }

        binding.createToDoButton.apply {

            setOnClickListener {
                Intent(this.context, AddActivity::class.java).apply {
                    startActivity(this)
                }
            }


        }

        binding.updateButton.apply {
            setOnClickListener {
                mainViewModel.listTask.clear()
                mainViewModel.addAllData()
                taskAdapter?.setData(mainViewModel.listTask)
            }
        }
    }

    private fun setUpAdapter() {
        taskAdapter = ListTaskAdapter(
            listener = {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra(INTENT_PARCELABLE, it)
                startActivity(intent)
            },
            onDeleteItem = { task ->
                mainViewModel.deleteData(task.id.toString())
                taskAdapter?.removeTask(task)

            }
        ).apply {
            mainViewModel.addAllData()
            setData(mainViewModel.listTask)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.apply {
            inflate(R.menu.main,menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.delete_all){
            mainViewModel.deleteAllData()
            taskAdapter?.removeAllItem()
        }
        return super.onOptionsItemSelected(item)
    }

}