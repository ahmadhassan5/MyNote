package com.ahmadhassan.mynote.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.ahmadhassan.mynote.NoteDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

/**
 * Created by Ahmad Hassan on 11/12/2022.
 */

actual fun Scope.createDriver(): SqlDriver {
    return AndroidSqliteDriver(NoteDatabase.Schema, androidContext(), "note.db")
}