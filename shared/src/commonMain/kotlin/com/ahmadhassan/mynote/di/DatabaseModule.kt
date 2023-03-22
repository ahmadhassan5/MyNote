package com.ahmadhassan.mynote.di

import com.ahmadhassan.mynote.domain.note.NoteDataSource
import com.ahmadhassan.mynote.NoteDatabase
import com.ahmadhassan.mynote.data.local.createDriver
import com.ahmadhassan.mynote.data.note.SQLDelightDataSource
import org.koin.dsl.module

/**
 * Created by Ahmad Hassan on 15/03/2023.
 */

val databaseModule = module {
    single { createDriver() }
    single<NoteDataSource> { SQLDelightDataSource(NoteDatabase(driver = get())) }
}