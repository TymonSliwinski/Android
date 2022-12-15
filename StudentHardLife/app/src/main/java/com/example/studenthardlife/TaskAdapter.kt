package com.example.studenthardlife

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val DAO: DAO)
    : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = DAO.getTasks()[position]
        holder.textViewTitle.text = item.title
        holder.textViewSubject.text = item.subject
        Log.d("TASK adapter: ", item.id.toString())
    }

    override fun getItemCount() = DAO.getTasks().size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.list_task_title)
        val textViewSubject: TextView = itemView.findViewById(R.id.list_task_subject)

        init {
            itemView.setOnClickListener {
                val task = DAO.getTasks()[adapterPosition]
                val bundle = Bundle()
                bundle.putInt("id", task.id)
                bundle.putString("title", task.title)
                bundle.putString("subject", task.subject)
                bundle.putString("description", task.description)

                Navigation.findNavController(itemView).navigate(R.id.action_task_list_fragment_to_task_details_fragment, bundle)
            }
        }
    }
}