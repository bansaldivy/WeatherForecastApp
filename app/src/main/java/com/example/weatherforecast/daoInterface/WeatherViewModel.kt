package com.example.weatherforecast.daoInterface

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.Database.weatherDatabase
import com.example.weatherforecast.Model.MyDataToday
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

   // private val  readAllWeatherData : LiveData<List<MyDataToday>>
    private val repository: WeatherRepository

    init {
        val weatherDao = weatherDatabase.getInstance(application).weatherDao()
        repository = WeatherRepository(weatherDao)
       // readAllWeatherData = repository.readAllWeatherData
    }

    fun addWeather(myDataToday: MyDataToday){
        viewModelScope.launch { Dispatchers.IO }
        repository.addWeather(myDataToday)
    }

}