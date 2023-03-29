package com.ahmadhassan.mynote.ui

/**
 * Created by Ahmad Hassan on 26/03/2023.
 */

sealed class Screen {
    data class NoteList(val route: String = "/list"): Screen()
    data class NoteDetail(val route: String = "/detail"): Screen()
}
