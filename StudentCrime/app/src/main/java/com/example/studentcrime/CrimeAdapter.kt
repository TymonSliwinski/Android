package com.example.studentcrime
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class CrimeAdapter(private val crimeList: ArrayList<CrimeData>) : RecyclerView.Adapter<CrimeAdapter.CrimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crime, parent, false)
        return CrimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: CrimeViewHolder, position: Int) {
        val crimeData = crimeList[position]

        holder.textViewName.text = crimeData.crimeName
        holder.imageView.visibility = if (crimeData.isSolved) View.VISIBLE else View.INVISIBLE
    }

    override fun getItemCount(): Int {
        return crimeList.size
    }

    inner class CrimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.handcuffs)
        val textViewName: TextView = itemView.findViewById(R.id.crime_name)

        init {
            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("title", crimeList[adapterPosition].crimeName)
                bundle.putString("description", crimeList[adapterPosition].crimeDescription)
                bundle.putString("studentId", "${crimeList[adapterPosition].studentId}")
                bundle.putString("isSolved", if (crimeList[adapterPosition].isSolved) "true" else "false")

                Navigation.findNavController(itemView).navigate(R.id.action_list_fragment_to_detail_fragment, bundle)
            }
        }
    }
}
