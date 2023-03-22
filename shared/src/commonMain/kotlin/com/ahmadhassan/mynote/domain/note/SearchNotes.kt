package com.ahmadhassan.mynote.domain.note

import com.ahmadhassan.mynote.domain.date.DateTimeUtil

/**
 * Created by Ahmad Hassan on 11/12/2022.
 */

class SearchNotes {
    fun execute(notes: List<Note>, query: String): List<Note> {
        if (query.isBlank())
            return notes
        return notes.filter {
            it.title.trim().lowercase().contains(query.lowercase()) ||
                    it.content.trim().lowercase().contains(query.lowercase())
        }.sortedBy { DateTimeUtil.toEpochMillis(it.createdAt) }
    }
}