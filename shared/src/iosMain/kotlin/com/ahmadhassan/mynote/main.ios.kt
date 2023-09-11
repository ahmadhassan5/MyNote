package com.ahmadhassan.mynote

import com.ahmadhassan.mynote.di.initKoin
import com.ahmadhassan.mynote.ui.NoteRoot
import moe.tlaster.precompose.PreComposeApplication
import platform.UIKit.UIViewController

/**
 * Created by Ahmad Hassan on 16/03/2023.
 */

fun MainViewController(): UIViewController {

    initKoin()
    return PreComposeApplication {
        NoteRoot()
    }
}