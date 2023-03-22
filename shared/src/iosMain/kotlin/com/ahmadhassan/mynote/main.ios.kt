package com.ahmadhassan.mynote

import androidx.compose.ui.window.Application
import com.ahmadhassan.mynote.ui.NoteRoot
import com.ahmadhassan.mynote.ui.NoteRootComponent
import com.ahmadhassan.mynote.di.initKoin
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import platform.UIKit.UIViewController

/**
 * Created by Ahmad Hassan on 16/03/2023.
 */

fun MainViewController(): UIViewController {

    initKoin()

    val rootComponent =
        NoteRootComponent(
            componentContext = DefaultComponentContext(lifecycle = LifecycleRegistry())
        )

    return Application("My iOS") {
        NoteRoot(rootComponent)
    }
}