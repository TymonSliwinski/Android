package com.example.studentcrime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_fragment, container, false)

        view.findViewById<Button>(R.id.back_button).setOnClickListener {
            Navigation.findNavController(view).navigate(
                DetailFragmentDirections.actionDetailFragmentToListFragment()
            )
        }

        view.findViewById<TextView>(R.id.details_crime_title).text = arguments?.getString("title")
        view.findViewById<TextView>(R.id.details_crime_description).text = arguments?.getString("description")
        view.findViewById<TextView>(R.id.details_student_index).text = arguments?.getString("studentId")
        view.findViewById<TextView>(R.id.details_is_solved).text = arguments?.getString("isSolved")

        return view
    }
}