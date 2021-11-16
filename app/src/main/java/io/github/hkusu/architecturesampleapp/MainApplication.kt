package io.github.hkusu.architecturesampleapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application()

fun plus(a: Int, b:Int): Int {
    return a + b
}

