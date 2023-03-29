package com.ahmadhassan.mynote.ui

import androidx.compose.runtime.Composable
import com.ahmadhassan.mynote.ui.note_detail.NoteDetailScreen
import com.ahmadhassan.mynote.ui.note_list.NoteListScreen
import com.ahmadhassan.mynote.ui.theme.MyApplicationTheme
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator

/**
 * Created by Ahmad Hassan on 16/03/2023.
 */

@Composable
internal fun NoteRoot() {

    val navigator = rememberNavigator()
    MyApplicationTheme {
        NavHost(navigator = navigator, initialRoute = Screen.NoteList().route) {
            scene(Screen.NoteList().route) {
                NoteListScreen(onAddORItemClicked = {
                    navigator.navigate("${Screen.NoteDetail().route}/${it}")
                })
            }
            scene("${Screen.NoteDetail().route}/{id:-?[0-9]+}") { backStackEntry ->
                backStackEntry.path<Long>("id")?.let {
                    NoteDetailScreen(noteId = it, onBackPressed = {
                        navigator.goBack()
                    })
                }
            }
        }
    }

}