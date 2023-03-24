package com.ahmadhassan.mynote.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.ahmadhassan.mynote.NoteDatabase
import org.koin.core.scope.Scope

/**
 * Created by Ahmad Hassan on 11/12/2022.
 */

actual fun Scope.createDriver(): SqlDriver {
    val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    NoteDatabase.Schema.create(driver)
    return driver
}