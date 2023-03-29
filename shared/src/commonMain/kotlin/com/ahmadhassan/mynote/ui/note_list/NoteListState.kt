package com.ahmadhassan.mynote.ui.note_list

import com.ahmadhassan.mynote.domain.note.Note

/**
 * Created by Ahmad Hassan on 11/12/2022.
 */

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)