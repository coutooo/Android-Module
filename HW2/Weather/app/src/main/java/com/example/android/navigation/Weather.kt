package pt.ua.nextweather.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class CallResponse {

    data class City(
        val owner:String,
        val country:String,
        val data:Array<Weather>
    )

    data class Weather(
        val  precipitaProb:Double,
        val  tMin: Double,
        val  tMax:Double,
        val predWindDir:String,
        val  idWeatherType:Int,
        val classWindSpeed:Int,
        val longitude:String,
        val forecastDate:String,
        val latitude:String
    )
}