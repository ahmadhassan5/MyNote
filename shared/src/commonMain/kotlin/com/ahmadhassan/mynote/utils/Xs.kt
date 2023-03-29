package com.ahmadhassan.mynote.utils

/**
 * Created by Ahmad Hassan on 20/03/2023.
 */

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.core.Koin
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.mp.KoinPlatformTools

/*@Composable
inline fun <reified T> getRemember(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
): T = remember(qualifier, parameters) {
    get(qualifier, parameters)
}

@Composable
fun getKoinRemember(): Koin = remember {
    getKoin()
}*/

inline fun <reified T> get(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
): T = KoinPlatformTools.defaultContext().get().get(qualifier, parameters)

fun getKoin(): Koin = KoinPlatformTools.defaultContext().get()