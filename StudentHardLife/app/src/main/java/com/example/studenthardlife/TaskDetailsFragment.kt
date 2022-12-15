package com.example.studenthardlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class TaskDetailsFragment: Fragment() {
    private lateinit var DAO: DAO

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.task_details_fragment, container, false)
        val id: Int? = arguments?.getInt("id")

        context?.let {
            DAO = DAO(it)
        }

        id?.let {
            view.findViewById<EditText>(R.id.task_title).setText(arguments?.getString("title"))
            view.findViewById<EditText>(R.id.task_subject).setText(arguments?.getString("subject"))
            view.findViewById<EditText>(R.id.task_description).setText(arguments?.getString("description"))
        }

        fun goBack() = Navigation.findNavController(view).navigate(
            TaskDetailsFragmentDirections.actionTaskDetailsFragmentToTaskListFragment()
        )

        view.findViewById<Button>(R.id.save_task_button).setOnClickListener {
            val title: String = view.findViewById<EditText>(R.id.task_title).text.toString()
            val subject: String = view.findViewById<EditText>(R.id.task_subject).text.toString()
            val description: String = view.findViewById<EditText>(R.id.task_description).text.toString()
            id?.let {
                DAO.updateTask(id, title, subject, description)
            } ?: DAO.addTask(Task(0, title, subject, description))
            goBack()
        }

        view.findViewById<Button>(R.id.delete_task_button).setOnClickListener {
            id?.let {
                DAO.deleteTask(it)
            }
            goBack()
        }


        return view
    }
}