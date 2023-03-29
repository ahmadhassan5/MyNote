package com.ahmadhassan.mynote.utils

/**
 * Created by Ahmad Hassan on 29/03/2023.
 */

sealed class UIEvent {
    data class ShowSnackBar(
        val message: String,
        val action: String? = null
    ): UIEvent()
}
