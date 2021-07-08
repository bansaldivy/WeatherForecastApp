package com.example.weatherforecast

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.Activity.RatingActivity
import com.example.weatherforecast.Adapter.RecyclerAdapter
import com.example.weatherforecast.Database.weatherDatabase
import com.example.weatherforecast.Model.MyDataToday
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var progressDialog: ProgressDialog
    lateinit var database: weatherDatabase

    lateinit var spinner: Spinner
    val items = arrayOf("Alwar", "Chennai", "Delhi", "Kolkata", "Mumbai", "Jaipur", "Goa", "Noida")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val mutableMap: MutableMap<String, Int> = mutableMapOf<String, Int>(
            "Alwar" to 190214,
            "Chennai" to 206671,
            "Delhi" to 202396,
            "Kolkata" to 206690,
            "Mumbai" to 204842,
            "Jaipur" to 205617,
            "Goa" to 262590,
            "Noida" to 3146227
        )
        val adapter = ArrayAdapter<String>(
            this, R.layout.spinner_item,
            items
        )
        adapter.setDropDownViewResource(R.layout.spinner_item)
        spinner.setAdapter(adapter)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                arg0: AdapterView<*>?,
                arg1: View?,
                arg2: Int,
                arg3: Long
            ) {
                val items = spinner.selectedItem.toString()
                DeleteTask().execute() //this was need to create bcoz in absence of this list of cities were appending

                Toast.makeText(this@MainActivity, "Weather Forecast of $items", Toast.LENGTH_LONG)
                    .show()

                AsyncTaskHandler()
                    .execute(
                        "http://dataservice.accuweather.com/forecasts/v1/daily/5day/${mutableMap.getValue(
                            items
                        )}?apikey=XCdKFarkZZVp3YpRGLxHdrsjcone9wEO%20&metric=true"
                    )

            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
                AsyncTaskHandler()
                    .execute(
                        "http://dataservice.accuweather.com/forecasts/v1/daily/5day/190214?apikey=XCdKFarkZZVp3YpRGLxHdrsjcone9wEO%20&metric=true"
                    )
            }
        }
        //val url: String = "https://api.openweathermap.org/data/2.5/weather?q=alwar,in&appid=8249605de50e982447e91f20ba3d4f73"
        // http://dataservice.accuweather.com/locations/v1/cities/search?apikey=XCdKFarkZZVp3YpRGLxHdrsjcone9wEO&q=Alwar
        //val url: String = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/190214?apikey=XCdKFarkZZVp3YpRGLxHdrsjcone9wEO%20&metric=true"
        // val url: String = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/190214?apikey=rXnROA5BN50vN5SNv1F8e8E1Dabc5U2Y&metric=true"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
        //return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                Toast.makeText(applicationContext, "Share", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_rateOurApp -> {
                intent = Intent(applicationContext, RatingActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_settings -> {
                Toast.makeText(applicationContext, "Settings", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    inner class AsyncTaskHandler : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
/*

            progressDialog = ProgressDialog(this@MainActivity)
            progressDialog.setMessage("Please Wait")
            progressDialog.setCancelable(false)
            progressDialog.show()
*/
        }

        override fun doInBackground(vararg url: String?): String {
            val res: String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                res = connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
            } finally {
                connection.disconnect()
            }
            //val res = assets.open("flicker_feeds.json").bufferedReader().use { reader->reader.readText() }
            jsonResult(res)
            return res
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            ReadTask().execute()
            /*if (progressDialog.isShowing)
                progressDialog.dismiss()*/
        }

        private fun jsonResult(jsonString: String?) {
            val plist = ArrayList<MyDataToday>() //Needed if we Call via Local Storage or JSON

            var i = 0

            val jsonObj = JSONObject(jsonString)

            val weather = jsonObj.getJSONArray("DailyForecasts")
            while (i < 1) {

                val jsonObject = weather.getJSONObject(i)
                val temperatureObj = jsonObject.getJSONObject("Temperature")
                val iconObj = jsonObject.getJSONObject("Day")

                /*plist.add(
                    MyDataToday(
                        jsonObject.getString("Date").substring(5,10),
                            temperatureObj.getJSONObject("Minimum").getString("Value"),
                            temperatureObj.getJSONObject("Maximum").getString("Value"),
                            iconObj.getInt("Icon"),
                            iconObj.getString("HasPrecipitation"),
                            jsonObject.getString("MobileLink"),
                            RecyclerAdapter.VIEW_TYPE_TODAY
                    )
                )*/
                i++
                val myDataToday = MyDataToday(
                    jsonObject.getString("Date").substring(5, 10),
                    temperatureObj.getJSONObject("Minimum").getString("Value"),
                    temperatureObj.getJSONObject("Maximum").getString("Value"),
                    iconObj.getInt("Icon"),
                    iconObj.getString("HasPrecipitation"),
                    jsonObject.getString("MobileLink"),
                    RecyclerAdapter.VIEW_TYPE_TODAY
                )
                weatherDatabase.getInstance(applicationContext).weatherDao().addWeather(myDataToday)
            }
            while (i < weather.length()) {
                val jsonObject = weather.getJSONObject(i)
                val temperatureObj = jsonObject.getJSONObject("Temperature")
                val iconObj = jsonObject.getJSONObject("Day")

                /*plist.add(
                        MyDataToday(
                                jsonObject.getString("Date").substring(5,10),
                                temperatureObj.getJSONObject("Minimum").getString("Value"),
                                temperatureObj.getJSONObject("Maximum").getString("Value"),
                                iconObj.getInt("Icon"),
                                iconObj.getString("HasPrecipitation"),
                                jsonObject.getString("MobileLink"),
                                RecyclerAdapter.VIEW_TYPE_OTHER
                        )
                )*/
                val myDataToday = MyDataToday(
                    jsonObject.getString("Date").substring(5, 10),
                    temperatureObj.getJSONObject("Minimum").getString("Value"),
                    temperatureObj.getJSONObject("Maximum").getString("Value"),
                    iconObj.getInt("Icon"),
                    iconObj.getString("HasPrecipitation"),
                    jsonObject.getString("MobileLink"),
                    RecyclerAdapter.VIEW_TYPE_OTHER
                )
                weatherDatabase.getInstance(applicationContext).weatherDao().addWeather(myDataToday)
                i++
            }
        }
    }

    inner class ReadTask : AsyncTask<String, String, List<MyDataToday>>() {

        override fun doInBackground(vararg params: String?): List<MyDataToday> {
            return weatherDatabase.getInstance(applicationContext).weatherDao().readAllWeatherData()
        }

        override fun onPostExecute(result: List<MyDataToday>?) {
            super.onPostExecute(result)

            val layoutManager = LinearLayoutManager(this@MainActivity)
            findViewById<RecyclerView>(R.id.rvWeather).layoutManager = layoutManager

            val rvAdapter = RecyclerAdapter(applicationContext, result ?: listOf())
            findViewById<RecyclerView>(R.id.rvWeather).adapter = rvAdapter
        }
    }

    inner class DeleteTask : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg params: String?): String {
            weatherDatabase.getInstance(applicationContext).weatherDao().deleteAllWeatherData()
            return params.toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

        }
    }
}