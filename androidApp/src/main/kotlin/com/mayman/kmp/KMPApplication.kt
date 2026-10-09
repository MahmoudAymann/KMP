package com.mayman.kmp

import android.app.Application
import com.mayman.kmp.di.initKoin
import org.koin.android.ext.koin.androidContext

//
// Created by Mahmoud Ayman Mostafa on 09/10/2026.
//
class KMPApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@KMPApplication)
        }
    }
}