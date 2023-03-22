package com.ahmadhassan.mynote.domain.note

import com.ahmadhassan.mynote.domain.note.Note

/**
 * Created by Ahmad Hassan on 11/12/2022.
 */

interface NoteDataSource {

    suspend fun insertNote(note: Note)

    suspend fun getNote(id: Long): Note?

    suspend fun getAllNotes(): List<Note>

    suspend fun deleteNote(id: Long)

}