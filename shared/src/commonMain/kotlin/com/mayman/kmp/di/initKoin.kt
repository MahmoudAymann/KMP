package com.mayman.kmp.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

//
// Created by Mahmoud Ayman Mostafa on 09/10/2026.
//

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule, platformModule)
    }
}