package com.ahmadhassan.mynote

import androidx.compose.runtime.Composable
import com.ahmadhassan.mynote.ui.NoteRoot
import com.ahmadhassan.mynote.ui.NoteRootComponent

/**
 * Created by Ahmad Hassan on 16/03/2023.
 */

@Composable
fun Application(component: NoteRootComponent) {
    NoteRoot(component)
}