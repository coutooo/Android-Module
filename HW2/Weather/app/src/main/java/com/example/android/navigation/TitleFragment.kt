package com.example.android.navigation

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding
import com.example.android.navigation.databinding.FragmentTitleBindingLandImpl
import com.google.gson.Gson
import okhttp3.*
import pt.ua.nextweather.datamodel.CallResponse
import java.io.IOException
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TitleFragment : Fragment() {

    val adapter = WeatherAdapter()
    private val client = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater, R.layout.fragment_title, container, false)

        val act = activity as MainActivity

        binding.buttonPorto.setOnClickListener {
            act.adapter.data= act.adapter.data+callApi("1131200")
            Log.println(Log.DEBUG, String(), act.adapter.data.toString())
            view?.findNavController()?.navigate(R.id.action_titleFragment_to_infoFragment)
        }
        binding.buttonLisboa.setOnClickListener {
            act.adapter.data= act.adapter.data+callApi("1110600")
            Log.println(Log.DEBUG, String(), act.adapter.data.toString())
            view?.findNavController()?.navigate(R.id.action_titleFragment_to_infoFragment)
        }
        binding.buttonAveiro.setOnClickListener {
            act.adapter.data= act.adapter.data+callApi("1010500")
            Log.println(Log.DEBUG, String(), act.adapter.data.toString())
            view?.findNavController()?.navigate(R.id.action_titleFragment_to_infoFragment)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    fun callApi(city: String): List<CallResponse.Weather>{
        val request = Request.Builder()
            .url("https://api.ipma.pt/open-data/forecast/meteorology/cities/daily/"+city+".json")
            .build()

        var ret= listOf<CallResponse.Weather>()

        var response= client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}

            override fun onResponse(call: Call, response: Response){

                var gson=Gson()
                var mCAll=gson?.fromJson(
                    response.body()?.string(),
                    CallResponse.City::class.java
                )

                 ret=mCAll.data.toList()
            }

        })
        Thread.sleep(2000)
        Log.println(Log.DEBUG, String(), ret.toString())
        return ret
    }

}