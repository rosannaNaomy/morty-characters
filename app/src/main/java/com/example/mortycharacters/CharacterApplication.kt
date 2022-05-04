package com.example.mortycharacters

import android.app.Application
import android.content.Context

class CharacterApplication: Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}