package com.ahmadhassan.mynote.ui.note_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmadhassan.mynote.ui.note_list.NoteListViewModel
import com.ahmadhassan.mynote.utils.get
import moe.tlaster.precompose.ui.viewModel

/**
 * Created by Ahmad Hassan on 13/12/2022.
 */

@Composable
fun NoteDetailScreen(
    noteId: Long?,
    onBackPressed: () -> Unit,
) {
    val viewModel = viewModel(NoteDetailViewModel::class, listOf(noteId)) {
        NoteDetailViewModel(noteId, get())
    }

    val state by viewModel.state.collectAsState()
    val noteHasBeenSaved by viewModel.hasNotBeenSaved.collectAsState()

    LaunchedEffect(key1 = noteHasBeenSaved) {
        if (noteHasBeenSaved) onBackPressed()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = viewModel::saveNote,
                backgroundColor = Color.Black,
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save Note",
                    tint = Color.White
                )
            }
        }, topBar = {
            TopAppBar(elevation = 0.dp, backgroundColor = Color(state.color), title = {
                Text("")
            }, navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(Icons.Default.ArrowBack, "", tint = Color.White)
                }
            })
        }
    ) { padding ->
        Column(
            modifier = Modifier.background(Color(state.color)).fillMaxSize().padding(padding)
                .padding(16.dp)
        ) {
            TransparentHintTextField(
                text = state.title,
                hint = "Enter a Title...",
                isHintVisible = state.isTitleHintVisible,
                onValueChanged = viewModel::onTitleChanged,
                onFocusChanged = {
                    viewModel.onTitleFocusChanged(it.isFocused)
                },
                isSingleLine = true,
                textStyle = TextStyle(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = state.content,
                hint = "Enter some content...",
                isHintVisible = state.isContentHintVisible,
                onValueChanged = viewModel::onContentChanged,
                onFocusChanged = {
                    viewModel.onContentFocusChanged(it.isFocused)
                },
                textStyle = TextStyle(fontSize = 20.sp),
                modifier = Modifier.weight(1f)
            )
        }
    }

}