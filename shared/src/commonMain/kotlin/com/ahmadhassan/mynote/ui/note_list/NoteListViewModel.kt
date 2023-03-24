package com.ahmadhassan.mynote.ui.note_list

import com.ahmadhassan.mynote.domain.note.Note
import com.ahmadhassan.mynote.domain.note.NoteDataSource
import com.ahmadhassan.mynote.domain.note.SearchNotes
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.cancel

/**
 * Created by Ahmad Hassan on 11/12/2022.
 */

class NoteListViewModelComponent constructor(
    private val componentContext: ComponentContext,
    private val noteDataSource: NoteDataSource,
    private val onAddORItemClicked: (Long?) -> Unit
): ComponentContext by componentContext {

    private val viewModelScope = CoroutineScope(Dispatchers.Main.immediate)
    private val searchNotes = SearchNotes()

    private val notes = MutableStateFlow(emptyList<Note>())
    private val searchText = MutableStateFlow("")
    private val isSearchActive = MutableStateFlow(false)

    val state = combine(notes, searchText, isSearchActive) {notes, searchText, isSearchActive ->
        NoteListState(
            notes = searchNotes.execute(notes, searchText),
            searchText = searchText,
            isSearchActive = isSearchActive
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteListState())

    init {
        lifecycle.doOnDestroy(viewModelScope::cancel)
    }

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

    fun onAddORItemClicked(id: Long?) {
        onAddORItemClicked.invoke(id)
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch {
            noteDataSource.deleteNote(id)
            loadNotes()
        }
    }
}