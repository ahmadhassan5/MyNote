package com.ahmadhassan.mynote.domain.date

import kotlinx.datetime.*

/**
 * Created by Ahmad Hassan on 11/12/2022.
 */

object DateTimeUtil {

    fun now(): LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    fun toEpochMillis(dateTime: LocalDateTime) = dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()

    fun formatNoteDate(dateTime: LocalDateTime): String {
        val month = dateTime.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }
        val day = if(dateTime.dayOfMonth < 10) "0${dateTime.dayOfMonth}" else dateTime.dayOfMonth
        val year = dateTime.year

        val hour = if (dateTime.hour < 10) "0${dateTime.hour}" else dateTime.hour
        val minute = if (dateTime.minute < 10) "0${dateTime.minute}" else dateTime.minute

        return buildString {
            append(month)
            append(" ")
            append(day)
            append(" ")
            append(year)
            append(", ")
            append(hour)
            append(":")
            append(minute)
        }
    }
}