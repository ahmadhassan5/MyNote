package com.ahmadhassan.mynote.ui.note_detail

import com.ahmadhassan.mynote.domain.date.DateTimeUtil
import com.ahmadhassan.mynote.domain.note.Note
import com.ahmadhassan.mynote.domain.note.NoteDataSource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

/**
 * Created by Ahmad Hassan on 12/12/2022.
 */

class NoteDetailViewModel(
    noteId: Long?,
    private val noteDataSource: NoteDataSource,
) : ViewModel() {

    private val title = MutableStateFlow("")
    private val isTitleTextFocused = MutableStateFlow(false)
    private val isContentTextFocused = MutableStateFlow(false)
    private val content = MutableStateFlow("")
    private val color = MutableStateFlow(Note.generateRandomColor())

    val state = combine(
        title,
        isTitleTextFocused,
        content,
        isContentTextFocused,
        color
    ) { title, isTitleTextFocused, content, isContentTextFocused, color ->
        NoteDetailState(
            title = title,
            isTitleHintVisible = title.isEmpty() && !isTitleTextFocused,
            content = content,
            isContentHintVisible = content.isEmpty() && !isContentTextFocused,
            color = color
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteDetailState())

    private val _hasNoteBeenSaved = MutableStateFlow(false)
    val hasNotBeenSaved = _hasNoteBeenSaved.asStateFlow()

    private var existingId: Long? = null

    init {
        noteId?.let {
            if (it == -1L)
                return@let

            this.existingId = it
            viewModelScope.launch {
                noteDataSource.getNote(it)?.let { note ->
                    title.value = note.title
                    content.value = note.content
                    color.value = note.colorHex
                }
            }
        }
    }

    fun onTitleChanged(text: String) {
        title.value = text
    }

    fun onTitleFocusChanged(isFocused: Boolean) {
        isTitleTextFocused.value = isFocused
    }

    fun onContentChanged(text: String) {
        content.value = text
    }

    fun onContentFocusChanged(isFocused: Boolean) {
        isContentTextFocused.value = isFocused
    }

    fun saveNote() {
        viewModelScope.launch {
            noteDataSource.insertNote(
                Note(
                    id = existingId,
                    title = title.value,
                    content = content.value,
                    colorHex = color.value,
                    createdAt = DateTimeUtil.now()
                )
            )
            _hasNoteBeenSaved.value = true
        }
    }
}
