package com.example.weatherforecast

import android.app.Application
import androidx.room.Room
import com.example.weatherforecast.Database.weatherDatabase

class App: Application() {

    companion object{
        lateinit var database: weatherDatabase
    }

    override fun onCreate() {
        super.onCreate()
        App.database = Room.databaseBuilder(this, weatherDatabase::class.java,"weather.db").build()
    }
}