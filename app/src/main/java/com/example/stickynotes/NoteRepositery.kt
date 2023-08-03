package com.example.stickynotes

import androidx.lifecycle.LiveData

class NoteRepositery(private val notedao : NoteDao) {

    val allNotes : LiveData<List<Note>> = notedao.getAllNotes()
    suspend fun insert(note : Note){
        notedao.insert(note)
    }
    suspend fun delete(note : Note){
        notedao.delete(note)
    }
}