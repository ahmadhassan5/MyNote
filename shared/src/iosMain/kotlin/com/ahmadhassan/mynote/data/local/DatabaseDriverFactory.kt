package com.ahmadhassan.mynote.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.ahmadhassan.mynote.NoteDatabase
import org.koin.core.scope.Scope

/**
 * Created by Ahmad Hassan on 11/12/2022.
 */

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver = NativeSqliteDriver(NoteDatabase.Schema, "note.db")
}

actual fun Scope.createDriver(): SqlDriver {
    return NativeSqliteDriver(NoteDatabase.Schema, "note.db")
}