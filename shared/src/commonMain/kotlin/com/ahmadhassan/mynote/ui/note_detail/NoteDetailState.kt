package com.ahmadhassan.mynote.ui.note_detail

import com.arkivanov.essenty.instancekeeper.InstanceKeeper

/**
 * Created by Ahmad Hassan on 12/12/2022.
 */

data class NoteDetailState(
    val title: String = "",
    val isTitleHintVisible: Boolean = false,
    val content: String = "",
    val isContentHintVisible: Boolean = false,
    val color: Long = 0xFFFFFFFF
): InstanceKeeper.Instance {
    override fun onDestroy() {

    }
}
