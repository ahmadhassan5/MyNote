package com.ahmadhassan.mynote.ui

import androidx.compose.runtime.Composable
import com.ahmadhassan.mynote.ui.note_detail.NoteDetailScreen
import com.ahmadhassan.mynote.ui.note_list.NoteListScreen
import com.ahmadhassan.mynote.ui.theme.MyApplicationTheme
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.*

/**
 * Created by Ahmad Hassan on 16/03/2023.
 */

@Composable
internal fun NoteRoot(component: NoteRootComponent) {

    MyApplicationTheme {
        Children(stack = component.stack, animation = stackAnimation(fade() + scale())) {
            when (val child = it.instance) {
                is NoteRootComponent.Child.List -> NoteListScreen(child.component)
                is NoteRootComponent.Child.Details -> NoteDetailScreen(child.component)
            }
        }
    }

}