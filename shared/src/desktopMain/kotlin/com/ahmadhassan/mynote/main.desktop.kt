package com.ahmadhassan.mynote

import androidx.compose.runtime.Composable
import com.ahmadhassan.mynote.di.initKoin
import com.ahmadhassan.mynote.ui.NoteRoot
import com.ahmadhassan.mynote.ui.NoteRootComponent
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry

/**
 * Created by Ahmad Hassan on 22/03/2023.
 */

@Composable fun Application(noteRootComponent: NoteRootComponent) {
    NoteRoot(noteRootComponent)
}