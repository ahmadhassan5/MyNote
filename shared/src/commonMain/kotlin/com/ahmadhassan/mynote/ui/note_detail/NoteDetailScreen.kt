package com.ahmadhassan.mynote.ui.note_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
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

/**
 * Created by Ahmad Hassan on 13/12/2022.
 */

@Composable
internal fun NoteDetailScreen(
    component: NoteDetailComponent,
) {
    
    val state by component.state.collectAsState()
    val noteHasBeenSaved by component.viewModel.hasNotBeenSaved.collectAsState()
    
    LaunchedEffect(key1 = noteHasBeenSaved) {
        if (noteHasBeenSaved)
            component.viewModel.onBackPressed()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = component.viewModel::saveNote,
                backgroundColor = Color.Black,
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save Note",
                    tint = Color.White
                )
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .background(Color(state.color))
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TransparentHintTextField(
                text = state.title,
                hint = "Enter a Title...",
                isHintVisible = state.isTitleHintVisible,
                onValueChanged = component.viewModel::onTitleChanged,
                onFocusChanged = {
                    component.viewModel.onTitleFocusChanged(it.isFocused)
                },
                isSingleLine = true,
                textStyle = TextStyle(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = state.content,
                hint = "Enter some content...",
                isHintVisible = state.isContentHintVisible,
                onValueChanged = component.viewModel::onContentChanged,
                onFocusChanged = {
                    component.viewModel.onContentFocusChanged(it.isFocused)
                },
                textStyle = TextStyle(fontSize = 20.sp),
                modifier = Modifier.weight(1f )
            )
        }
    }
    
}