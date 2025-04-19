package com.example.smartdailycompanion

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartdailycompanion.databinding.ActivityTodoBinding

class TodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoBinding
    private val todoList = mutableListOf<String>() // We'll replace this with Room later
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TodoAdapter(todoList)
        binding.rvToDos.layoutManager = LinearLayoutManager(this)
        binding.rvToDos.adapter = adapter

        binding.btnAddToDo.setOnClickListener {
            val newTask = "Task ${todoList.size + 1}" // temp task text
            todoList.add(newTask)
            adapter.notifyItemInserted(todoList.size - 1)
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show()
        }
    }
}
