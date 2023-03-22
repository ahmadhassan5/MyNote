package com.ahmadhassan.mynote.data.note

import com.ahmadhassan.mynote.domain.note.Note
import com.ahmadhassan.mynote.domain.note.NoteDataSource
import com.ahmadhassan.mynote.NoteDatabase
import com.ahmadhassan.mynote.domain.date.DateTimeUtil

/**
 * Created by Ahmad Hassan on 11/12/2022.
 */

class SQLDelightDataSource(
    database: NoteDatabase
): NoteDataSource {

    val queries = database.noteQueries

    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            note.id,
            note.title,
            note.content,
            note.colorHex,
            DateTimeUtil.toEpochMillis(note.createdAt)
        )
    }

    override suspend fun getNote(id: Long): Note? {
        return queries
            .getNote(id)
            .executeAsOneOrNull()
            ?.toNote()
    }

    override suspend fun getAllNotes(): List<Note> {
        return queries
            .getAllNotes()
            .executeAsList()
            .map { it.toNote() }
    }

    override suspend fun deleteNote(id: Long) {
        queries.deleteNote(id)
    }
}