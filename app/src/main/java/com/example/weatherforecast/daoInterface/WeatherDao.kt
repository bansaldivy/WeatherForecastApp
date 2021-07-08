package com.example.weatherforecast.daoInterface

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weatherforecast.Model.MyDataToday

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addWeather(myDataToday: MyDataToday)

    @Query("SELECT * FROM weather_table")
    fun readAllWeatherData(): List<MyDataToday>

    @Query("DELETE FROM weather_table")
    fun deleteAllWeatherData() : Unit
}