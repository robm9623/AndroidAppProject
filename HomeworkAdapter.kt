package com.example.homeworkremindmeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeworkAdapter(private val homeworkList: ArrayList<String>) :
    RecyclerView.Adapter<HomeworkAdapter.HomeworkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeworkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_homework, parent, false)
        return HomeworkViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeworkViewHolder, position: Int) {
        val homeworkItem = homeworkList[position]
        holder.homeworkTask.text = homeworkItem
    }

    override fun getItemCount(): Int = homeworkList.size

    class HomeworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val homeworkTask: TextView = itemView.findViewById(R.id.homeworkTask)
    }
}
