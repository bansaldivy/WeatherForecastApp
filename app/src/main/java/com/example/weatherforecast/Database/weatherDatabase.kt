package com.example.weatherforecast.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.weatherforecast.Model.MyDataToday
import com.example.weatherforecast.daoInterface.WeatherDao

@Database(entities = [MyDataToday::class], version = 1)
abstract class weatherDatabase : RoomDatabase(){

    abstract fun weatherDao(): WeatherDao

    companion object{
        @Volatile //visible to other threads
        private var INSTANCE: weatherDatabase? = null

        @Synchronized
        fun getInstance(context: Context): weatherDatabase{
            val tempInstance = INSTANCE
            if(tempInstance == null) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    weatherDatabase::class.java,
                    "weather_database.db"
                ).build()
                INSTANCE = instance
                return instance
            }
            else
            return tempInstance
        }

        /*private val roomCallBack = object : Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(instance!!)
            }
        }
        private fun populateDatabase(db: weatherDatabase) {
            val noteDao = db.WeatherDao()
            //subscribeOnBackground {
                //noteDao.insert(MyDataToday("title 1", "desc 1", 1))

            //}
        }*/
    }
}