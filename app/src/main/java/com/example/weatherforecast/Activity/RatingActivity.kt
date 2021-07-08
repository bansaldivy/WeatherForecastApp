package com.example.weatherforecast.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.weatherforecast.R
import com.hsalf.smilerating.BaseRating
import com.hsalf.smilerating.SmileRating

class RatingActivity : AppCompatActivity() {
    companion object {
        private val TAG = "App"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ratingbar)

        val smileRating : SmileRating = findViewById(R.id.smile_rating)

        smileRating.setOnSmileySelectionListener(object : SmileRating.OnSmileySelectionListener{

            override fun onSmileySelected(@BaseRating.Smiley smiley:Int, reselected:Boolean) {
                // Retrieve the value of the bar dinamically
                // level is from 1 to 5
                // Will return 0 if NONE selected
                val level = smileRating.selectedSmile
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                when (smiley) {
                    SmileRating.BAD -> Log.i(TAG, "Bad")
                    SmileRating.GOOD -> Log.i(TAG, "Good")
                    SmileRating.GREAT -> Log.i(TAG, "Great")
                    SmileRating.OKAY -> Log.i(TAG, "Okay")
                    SmileRating.TERRIBLE -> Log.i(TAG, "Terrible")
                }

                when(level){
                    SmileRating.BAD -> Toast.makeText(this@RatingActivity,"BAD SELECTED",Toast.LENGTH_SHORT).show()
                    SmileRating.GOOD -> Toast.makeText(this@RatingActivity,"GOOD SELECTED",Toast.LENGTH_SHORT).show()
                    SmileRating.GREAT -> Toast.makeText(this@RatingActivity,"GREAT SELECTED",Toast.LENGTH_SHORT).show()
                    SmileRating.OKAY -> Toast.makeText(this@RatingActivity,"OKAY SELECTED",Toast.LENGTH_SHORT).show()
                    SmileRating.TERRIBLE -> Toast.makeText(this@RatingActivity,"TERRIBLE SELECTED",Toast.LENGTH_SHORT).show()
                }


            }
        })
    }
}

