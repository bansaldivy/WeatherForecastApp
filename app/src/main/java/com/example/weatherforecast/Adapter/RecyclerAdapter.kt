package com.example.weatherforecast.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.Model.MyData
import com.example.weatherforecast.Model.MyDataToday
import com.example.weatherforecast.R

class RecyclerAdapter(context: Context, weatherList: List<MyDataToday>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedItemPosition: Int = 0

    companion object {
        const val VIEW_TYPE_TODAY = 1
        const val VIEW_TYPE_OTHER = 2
    }

    var weatherList: List<MyDataToday> = weatherList
    var context: Context = context

    inner class RecyclerViewHolderToday(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvWeather: TextView = itemView.findViewById(R.id.tvWeather)
        var tvMinTemp: TextView = itemView.findViewById(R.id.tvMinTemp)
        var tvMaxTemp: TextView = itemView.findViewById(R.id.tvMaxTemp)
        var ivWeather: ImageView = itemView.findViewById(R.id.ivWeather)
        var tvHasPrecipitation: TextView = itemView.findViewById(R.id.tvHasPrecipitation)
        var tvLink: TextView = itemView.findViewById(R.id.tvLink)
       // var rvWeather: RecyclerView = itemView.findViewById(R.id.rvWeather)


        fun bind(position: Int) {
            val weather = weatherList[position]

           // rvWeather.setBackgroundResource(R.drawable.bg)

            tvWeather.text = weather.Date + "-2021"
            tvMinTemp.text = weather.Value + "°C"
            tvMaxTemp.text = weather.Value1 + "°C"


            when (weather.Icon) {
                1 -> ivWeather.setImageResource(R.drawable.icon1)
                2 -> ivWeather.setImageResource(R.drawable.icon2)
                3 -> ivWeather.setImageResource(R.drawable.icon3)
                4 -> ivWeather.setImageResource(R.drawable.icon4)
                5 -> ivWeather.setImageResource(R.drawable.icon5)
                6 -> ivWeather.setImageResource(R.drawable.icon6)
                7 -> ivWeather.setImageResource(R.drawable.icon7)
                8 -> ivWeather.setImageResource(R.drawable.icon8)
                11 -> ivWeather.setImageResource(R.drawable.icon11)
                12 -> ivWeather.setImageResource(R.drawable.icon12)
                13 -> ivWeather.setImageResource(R.drawable.icon13)
                14 -> ivWeather.setImageResource(R.drawable.icon14)
                15 -> ivWeather.setImageResource(R.drawable.icon15)
                16, 17 -> ivWeather.setImageResource(R.drawable.icon16_17)
                18 -> ivWeather.setImageResource(R.drawable.icon18)
                19 -> ivWeather.setImageResource(R.drawable.icon19)
                20 -> ivWeather.setImageResource(R.drawable.icon20)
                21 -> ivWeather.setImageResource(R.drawable.icon21)
                30 -> ivWeather.setImageResource(R.drawable.icon30)
                31 -> ivWeather.setImageResource(R.drawable.icon31)
                32 -> ivWeather.setImageResource(R.drawable.icon32)
                33 -> ivWeather.setImageResource(R.drawable.icon33)
                34 -> ivWeather.setImageResource(R.drawable.icon34)
                35 -> ivWeather.setImageResource(R.drawable.icon35)
                36 -> ivWeather.setImageResource(R.drawable.icon36)
                37 -> ivWeather.setImageResource(R.drawable.icon37)
                38 -> ivWeather.setImageResource(R.drawable.icon38)
                39 -> ivWeather.setImageResource(R.drawable.icon39)
                40 -> ivWeather.setImageResource(R.drawable.icon40)
                41 -> ivWeather.setImageResource(R.drawable.icon41)
                42 -> ivWeather.setImageResource(R.drawable.icon42)
                43 -> ivWeather.setImageResource(R.drawable.icon43)
                44 -> ivWeather.setImageResource(R.drawable.icon44)
            }

            tvHasPrecipitation.text = weather.HasPrecipitation
            tvLink.text = weather.MobileLink
        }
    }

    inner class RecyclerViewHolderOther(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvWeather: TextView = itemView.findViewById(R.id.tvWeather)
        var tvMinTemp: TextView = itemView.findViewById(R.id.tvMinTemp)
        var tvMaxTemp: TextView = itemView.findViewById(R.id.tvMaxTemp)
        var ivWeather: ImageView = itemView.findViewById(R.id.ivWeather)

        fun bind(position: Int) {
            val weather = weatherList[position]

            tvWeather.text = weather.Date + "-2021"
            tvMinTemp.text = weather.Value + "°C"
            tvMaxTemp.text = weather.Value1 + "°C"

            when (weather.Icon) {
                1 -> ivWeather.setImageResource(R.drawable.icon1)
                2 -> ivWeather.setImageResource(R.drawable.icon2)
                3 -> ivWeather.setImageResource(R.drawable.icon3)
                4 -> ivWeather.setImageResource(R.drawable.icon4)
                5 -> ivWeather.setImageResource(R.drawable.icon5)
                6 -> ivWeather.setImageResource(R.drawable.icon6)
                7 -> ivWeather.setImageResource(R.drawable.icon7)
                8 -> ivWeather.setImageResource(R.drawable.icon8)
                11 -> ivWeather.setImageResource(R.drawable.icon11)
                12 -> ivWeather.setImageResource(R.drawable.icon12)
                13 -> ivWeather.setImageResource(R.drawable.icon13)
                14 -> ivWeather.setImageResource(R.drawable.icon14)
                15 -> ivWeather.setImageResource(R.drawable.icon15)
                16, 17 -> ivWeather.setImageResource(R.drawable.icon16_17)
                18 -> ivWeather.setImageResource(R.drawable.icon18)
                19 -> ivWeather.setImageResource(R.drawable.icon19)
                20 -> ivWeather.setImageResource(R.drawable.icon20)
                21 -> ivWeather.setImageResource(R.drawable.icon21)
                30 -> ivWeather.setImageResource(R.drawable.icon30)
                31 -> ivWeather.setImageResource(R.drawable.icon31)
                32 -> ivWeather.setImageResource(R.drawable.icon32)
                33 -> ivWeather.setImageResource(R.drawable.icon33)
                34 -> ivWeather.setImageResource(R.drawable.icon34)
                35 -> ivWeather.setImageResource(R.drawable.icon35)
                36 -> ivWeather.setImageResource(R.drawable.icon36)
                37 -> ivWeather.setImageResource(R.drawable.icon37)
                38 -> ivWeather.setImageResource(R.drawable.icon38)
                39 -> ivWeather.setImageResource(R.drawable.icon39)
                40 -> ivWeather.setImageResource(R.drawable.icon40)
                41 -> ivWeather.setImageResource(R.drawable.icon41)
                42 -> ivWeather.setImageResource(R.drawable.icon42)
                43 -> ivWeather.setImageResource(R.drawable.icon43)
                44 -> ivWeather.setImageResource(R.drawable.icon44)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_TODAY) {
            return RecyclerViewHolderToday(
                    LayoutInflater.from(parent.context).inflate(R.layout.weather_row_today_layout, parent, false)
            )
        }
        return RecyclerViewHolderOther(
                LayoutInflater.from(parent.context).inflate(R.layout.weather_row_layout, parent, false)
        )
        //return RecyclerViewHolder(view)

    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //val weather = weatherList[position]

        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }
        if (weatherList[position].viewType === VIEW_TYPE_TODAY) {
            (holder as RecyclerViewHolderToday).bind(position)

        } else {
            (holder as RecyclerViewHolderOther).bind(position)
        }

        if(selectedItemPosition == position){
            holder.itemView.setBackgroundColor(Color.parseColor("#6a5acd"))
        }
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#8470ff"))
    }

    override fun getItemViewType(position: Int): Int {
        return weatherList[position].viewType
    }
}
        /*holder.tvWeather.text = weather.Date + "-2021"
        holder.tvMinTemp.text = weather.Value + "°C"
        holder.tvMaxTemp.text = weather.Value1 + "°C"

        when(weather.Icon){
            1 -> holder.ivWeather.setImageResource(R.drawable.icon1)
            2 -> holder.ivWeather.setImageResource(R.drawable.icon2)
            3 -> holder.ivWeather.setImageResource(R.drawable.icon3)
            4 -> holder.ivWeather.setImageResource(R.drawable.icon4)
            5 -> holder.ivWeather.setImageResource(R.drawable.icon5)
            6 -> holder.ivWeather.setImageResource(R.drawable.icon6)
            7 -> holder.ivWeather.setImageResource(R.drawable.icon7)
            8 -> holder.ivWeather.setImageResource(R.drawable.icon8)
            11 -> holder.ivWeather.setImageResource(R.drawable.icon11)
            12 -> holder.ivWeather.setImageResource(R.drawable.icon12)
            13 -> holder.ivWeather.setImageResource(R.drawable.icon13)
            14 -> holder.ivWeather.setImageResource(R.drawable.icon14)
            15 -> holder.ivWeather.setImageResource(R.drawable.icon15)
            16,17 -> holder.ivWeather.setImageResource(R.drawable.icon16_17)
            18 -> holder.ivWeather.setImageResource(R.drawable.icon18)
            19 -> holder.ivWeather.setImageResource(R.drawable.icon19)
            20 -> holder.ivWeather.setImageResource(R.drawable.icon20)
            21 -> holder.ivWeather.setImageResource(R.drawable.icon21)
            30 -> holder.ivWeather.setImageResource(R.drawable.icon30)
            31 -> holder.ivWeather.setImageResource(R.drawable.icon31)
            32 -> holder.ivWeather.setImageResource(R.drawable.icon32)
            33 -> holder.ivWeather.setImageResource(R.drawable.icon33)
            34 -> holder.ivWeather.setImageResource(R.drawable.icon34)
            35 -> holder.ivWeather.setImageResource(R.drawable.icon35)
            36 -> holder.ivWeather.setImageResource(R.drawable.icon36)
            37 -> holder.ivWeather.setImageResource(R.drawable.icon37)
            38 -> holder.ivWeather.setImageResource(R.drawable.icon38)
            39 -> holder.ivWeather.setImageResource(R.drawable.icon39)
            40 -> holder.ivWeather.setImageResource(R.drawable.icon40)
            41 -> holder.ivWeather.setImageResource(R.drawable.icon41)
            42 -> holder.ivWeather.setImageResource(R.drawable.icon42)
            43 -> holder.ivWeather.setImageResource(R.drawable.icon43)
            44 -> holder.ivWeather.setImageResource(R.drawable.icon44)
        }*/

