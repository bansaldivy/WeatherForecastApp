package com.example.weatherforecast.daoInterface

import androidx.lifecycle.LiveData
import com.example.weatherforecast.Model.MyDataToday

class WeatherRepository(private val weatherDao: WeatherDao) {

    //val readAllWeatherData List<MyDataToday> = weatherDao.readAllWeatherData()

    fun addWeather(myDataToday: MyDataToday){
        weatherDao.addWeather(myDataToday)
    }

}