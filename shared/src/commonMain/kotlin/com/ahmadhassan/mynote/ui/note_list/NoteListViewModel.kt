package com.ahmadhassan.mynote.ui.note_list

import com.ahmadhassan.mynote.domain.note.Note
import com.ahmadhassan.mynote.domain.note.NoteDataSource
import com.ahmadhassan.mynote.domain.note.SearchNotes
import com.ahmadhassan.mynote.utils.UIEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

/**
 * Created by Ahmad Hassan on 11/12/2022.
 */

class NoteListViewModel constructor(
    private val noteDataSource: NoteDataSource,
): ViewModel() {

    private val searchNotes = SearchNotes()

    private val notes = MutableStateFlow(emptyList<Note>())
    private val searchText = MutableStateFlow("")
    private val isSearchActive = MutableStateFlow(false)

    private var deletedNote:Note? = null

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val state = combine(notes, searchText, isSearchActive) {notes, searchText, isSearchActive ->
        NoteListState(
            notes = searchNotes.execute(notes, searchText),
            searchText = searchText,
            isSearchActive = isSearchActive
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteListState())

    fun loadNotes() {
        viewModelScope.launch {
            notes.value = noteDataSource.getAllNotes()
        }
    }

    fun onSearchTextChange(searchText: String) {
        this.searchText.value = searchText
    }

    fun onToggleSearch() {
        isSearchActive.value = !isSearchActive.value
        if (!isSearchActive.value)
            searchText.value = ""
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deletedNote = note
            noteDataSource.deleteNote(note.id!!)
            loadNotes()
                _uiEvent.send(UIEvent.ShowSnackBar("Note deleted", "Undo"))
        }
    }

    fun undoDelete() {
        viewModelScope.launch {
            noteDataSource.insertNote(deletedNote!!)
            loadNotes()
        }
    }
}