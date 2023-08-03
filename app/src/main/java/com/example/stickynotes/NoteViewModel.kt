package com.example.stickynotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes : LiveData<List<Note>>
    val repositery : NoteRepositery
    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repositery = NoteRepositery(dao)
        allNotes = repositery.allNotes
    }

    fun deleteNode(note : Note) = viewModelScope.launch(Dispatchers.IO) {
   repositery.delete(note)
    }

    fun insertNote(note: Note)= viewModelScope.launch(Dispatchers.IO){
        repositery.insert(note)
    }
}