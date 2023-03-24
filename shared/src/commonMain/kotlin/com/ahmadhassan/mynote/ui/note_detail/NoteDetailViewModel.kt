package com.ahmadhassan.mynote.ui.note_detail

import com.ahmadhassan.mynote.domain.date.DateTimeUtil
import com.ahmadhassan.mynote.domain.note.Note
import com.ahmadhassan.mynote.domain.note.NoteDataSource
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Created by Ahmad Hassan on 12/12/2022.
 */

/*class NoteDetailViewModelComponent (
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    noteId: Long?,
    private val noteDataSource: NoteDataSource,
    private val onBackPressed: () -> Unit
): ComponentContext by componentContext {

    private val viewModelScope = coroutineScope(coroutineContext + SupervisorJob())
//    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

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

    fun onBackPressed() {
        onBackPressed.invoke()
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

}*/


class NoteDetailComponent(
    componentContext: ComponentContext,
    noteId: Long?,
    private val noteDataSource: NoteDataSource,
    private val onBackPressed: () -> Unit
) : ComponentContext by componentContext {

    val viewModel = instanceKeeper.getOrCreate {
        NoteDetailViewModel(
            noteId = noteId,
            noteDataSource = noteDataSource,
//            onBackPressed = onBackPressed,
            savedState = stateKeeper.consume(
                key = "viewModelState",
                NoteDetailViewModel.SavedState::class
            ),
        )
    }

    val state: StateFlow<NoteDetailState> get() = viewModel.state
    private val scope = CoroutineScope(Dispatchers.Main.immediate)

    init {
        stateKeeper.register(key = "viewModelState", supplier = viewModel::saveState)

        scope.launch {
            viewModel.hasNotBeenSaved.filter { it }.collect { onBackPressed() }
        }

        lifecycle.doOnDestroy(scope::cancel)


    }

    class NoteDetailViewModel(
        private val noteId: Long?,
        private val noteDataSource: NoteDataSource,
        savedState: SavedState?,
//    private val onBackPressed: () -> Unit
    ) : InstanceKeeper.Instance {

        private val viewModelScope = CoroutineScope(Dispatchers.Main.immediate)

        private val title = MutableStateFlow(savedState?.title ?: "")
        private val isTitleTextFocused = MutableStateFlow(savedState?.isTitleTextFocused ?: false)
        private val isContentTextFocused =
            MutableStateFlow(savedState?.isContentTextFocused ?: false)
        private val content = MutableStateFlow(savedState?.content ?: "")
        private val color = MutableStateFlow(savedState?.color ?: Note.generateRandomColor())

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

        init {
            noteId?.let {
                if (it == -1L)
                    return@let

//                this.existingId = it
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
                        id = noteId,
                        title = title.value,
                        content = content.value,
                        colorHex = color.value,
                        createdAt = DateTimeUtil.now()
                    )
                )
                _hasNoteBeenSaved.value = true
            }
        }

        fun onBackPressed() {
//        onBackPressed.invoke()
        }

        fun saveState(): SavedState =
            SavedState(
                title = title.value,
                isTitleTextFocused = isContentTextFocused.value,
                isContentTextFocused = isContentTextFocused.value,
                content = content.value,
                color = color.value,
            )

        override fun onDestroy() {
            viewModelScope.cancel()
        }

        @Parcelize
        class SavedState(
            val title: String,
            val isTitleTextFocused: Boolean,
            val isContentTextFocused: Boolean,
            val content: String,
            val color: Long,
        ) : Parcelable
    }
}

