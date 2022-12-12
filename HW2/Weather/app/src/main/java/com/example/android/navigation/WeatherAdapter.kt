package com.example.android.navigation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.ua.nextweather.datamodel.CallResponse

class WeatherAdapter: RecyclerView.Adapter<TextItemViewHolder>() {

    var data =  listOf<CallResponse.Weather>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        Log.d("olaaa", item.toString());
        holder.textView.text = item.forecastDate.toString()+"\nTemperatura: "+item.tMin.toString()+"º - "+item.tMax.toString()+"º\n" +
                "Precipitação: "+item.precipitaProb.toString()+"%\n____________________"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.item, parent, false) as TextView

        return TextItemViewHolder(view)
    }
}