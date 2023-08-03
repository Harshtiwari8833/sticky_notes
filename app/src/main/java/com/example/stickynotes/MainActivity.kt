package com.example.stickynotes

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesRAdapter {
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
  val recyclerView  = findViewById<RecyclerView>(R.id.recyclerView)
        val button = findViewById<Button>(R.id.button)
        val input = findViewById<EditText>(R.id.input)
        recyclerView.layoutManager = LinearLayoutManager(this )
        val adapter = NotesAdapter(this, this)
        recyclerView.adapter = adapter



        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }

        })

        button.setOnClickListener {
       val noteText = input.text.toString()
              if (noteText.isNotEmpty()){
                viewModel.insertNote(Note(noteText))
                Toast.makeText(this, "note added ", Toast.LENGTH_SHORT).show()
              }
        }

    }

    override fun OnItemClicked(note: Note) {
viewModel.deleteNode(note)
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
    }
}