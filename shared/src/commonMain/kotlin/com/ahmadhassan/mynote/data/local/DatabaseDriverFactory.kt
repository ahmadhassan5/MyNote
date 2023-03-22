package com.ahmadhassan.mynote.data.local

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope

/**
 * Created by Ahmad Hassan on 11/12/2022.
 */

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

expect fun Scope.createDriver(): SqlDriver