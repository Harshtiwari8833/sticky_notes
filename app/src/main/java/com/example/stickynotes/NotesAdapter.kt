package com.example.stickynotes

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NotesAdapter(val context : Context, val listner : INotesRAdapter) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
val allNotes = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {


        val viewHolder =  ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout, parent, false))
        viewHolder.deleteBtn.setOnClickListener{
     listner.OnItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return  viewHolder
    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
       val currentNote = allNotes[position]
        holder.textview.text = currentNote.text
    }

    override fun getItemCount(): Int {
      return allNotes.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val textview = itemView.findViewById<TextView>(R.id.text)
        val deleteBtn = itemView.findViewById<ImageView>(R.id.deleteBtn)
    }
    fun updateList(newList : List<Note>){
        this.allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface INotesRAdapter {
     fun OnItemClicked(note :Note)
}