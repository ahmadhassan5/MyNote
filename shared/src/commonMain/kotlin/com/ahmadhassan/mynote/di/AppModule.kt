package com.ahmadhassan.mynote.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * Created by Ahmad Hassan on 15/03/2023.
 */

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(databaseModule)
    }
}