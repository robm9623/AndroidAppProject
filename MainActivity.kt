package com.example.homeworkremindmeapp

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HomeworkAdapter
    private lateinit var homeworkList: ArrayList<String>
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        sharedPreferences = getSharedPreferences("HomeworkPrefs", MODE_PRIVATE)
        homeworkList = ArrayList(sharedPreferences.getStringSet("homeworkList", setOf()) ?: setOf())

        adapter = HomeworkAdapter(homeworkList)
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.addButton).setOnClickListener {
            showAddHomeworkDialog()
        }
    }

    private fun showAddHomeworkDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Homework")

        val viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_add_homework, null)
        val taskInput = viewInflated.findViewById<EditText>(R.id.AssignmentInput)
        val dueDateInput = viewInflated.findViewById<EditText>(R.id.DueDateInput)

        builder.setView(viewInflated)

        builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
            val task = taskInput.text.toString()
            val dueDate = dueDateInput.text.toString()

            if (task.isNotEmpty() && dueDate.isNotEmpty()) {
                val homeworkItem = "$task - Due: $dueDate"
                homeworkList.add(homeworkItem)
                adapter.notifyDataSetChanged()

                saveHomeworkList()

                dialog.dismiss()
            } else {
                Toast.makeText(this, "Please enter both task and due date", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun saveHomeworkList() {
        val homeworkSet = homeworkList.toSet()
        sharedPreferences.edit().putStringSet("homeworkList", homeworkSet).apply()
    }
}
