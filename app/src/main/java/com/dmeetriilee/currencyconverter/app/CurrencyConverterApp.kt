package com.dmeetriilee.currencyconverter.app

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.dmeetriilee.currencyconverter.di.ServiceLocator
import com.dmeetriilee.currencyconverter.preferences.Prefs
import com.dmeetriilee.currencyconverter.room.CurrencyDatabase


class CurrencyConverterApp : Application() {
    companion object {
        lateinit var instance: CurrencyConverterApp
            private set
    }

    lateinit var database: CurrencyDatabase
        private set
    lateinit var prefs: Prefs
        private set


    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        database = databaseBuilder(this, CurrencyDatabase::class.java, "database")
            .build()
        prefs = Prefs(context = instance.applicationContext)
        ServiceLocator.getInstance()
    }
}