package net.lpscream.weatherstudio.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import net.lpscream.weatherstudio.R
import net.lpscream.weatherstudio.adapters.WeatherListAdapter
import net.lpscream.weatherstudio.databinding.WeatherFragmentBinding
import java.text.SimpleDateFormat
import java.util.*

class WeatherFragment : Fragment() {

    private var _binding: WeatherFragmentBinding? = null
    private val binding get() = _binding!!
    private var query = String()
    private val TAG = "WeatherFragment_log"



    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        query = arguments?.getString("query").toString()
        Log.d(TAG, "onCreate: " + query)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = WeatherFragmentBinding.inflate(inflater, container, false)
        val view: View = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, WeatherViewModelFactory(query)).get(WeatherViewModel::class.java)


        viewModel.getWeather().observe(viewLifecycleOwner, Observer {
            binding.cityName.text = viewModel.getWeather().value?.city?.name
            //TODO fix sunset/sunrise time
            var simpleDateFormat = SimpleDateFormat("hh:mm:ss")
            binding.sunriseTextView.text = resources.getString(R.string.sunrise) + simpleDateFormat.format(Date(viewModel.getWeather().value?.city?.sunrise!!    * 1000L))
            binding.sunsetTextView.text = resources.getString(R.string.sunset) + simpleDateFormat.format(Date(viewModel.getWeather().value?.city?.sunset!!    * 1000L))
            var adapterList: WeatherListAdapter = WeatherListAdapter(requireContext(),viewModel.getWeatherList())
            binding.weatherRecyclerView.apply {
                setHasFixedSize(false)
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = adapterList
                adapterList.notifyDataSetChanged()
            }



        })
    }

}

/*

String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisLeft!!),
                TimeUnit.MILLISECONDS.toMinutes(millisLeft) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisLeft)),
                TimeUnit.MILLISECONDS.toSeconds(millisLeft) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisLeft)))
 */