package com.ahmadhassan.mynote.domain.note

import com.ahmadhassan.mynote.ui.theme.*
import kotlinx.datetime.LocalDateTime

/**
 * Created by Ahmad Hassan on 11/12/2022.
 */

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val createdAt: LocalDateTime
) {
    companion object {
        private val colors = listOf(RedOrangeHex, RedPinkHex, BabyBlueHex, VioletHex, LightGreenHex)

        fun generateRandomColor() = colors.random()
    }
}
