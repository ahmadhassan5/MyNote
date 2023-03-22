package com.ahmadhassan.mynote.data.note

import com.ahmadhassan.mynote.domain.note.Note
import database.NoteEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Created by Ahmad Hassan on 11/12/2022.
 */

fun NoteEntity.toNote() = Note(
    id = Id,
    title = Title,
    content = Content,
    colorHex = ColorHex,
    createdAt = Instant
        .fromEpochMilliseconds(CreatedAt)
        .toLocalDateTime(TimeZone.currentSystemDefault())
)