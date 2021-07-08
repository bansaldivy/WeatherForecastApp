package com.example.weatherforecast.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class MyDataToday(val Date: String,
                       val Value: String,
                       val Value1: String,
                       val Icon: Int,
                       val HasPrecipitation: String,
                       @PrimaryKey(autoGenerate = false) val MobileLink: String,
                       val viewType: Int)