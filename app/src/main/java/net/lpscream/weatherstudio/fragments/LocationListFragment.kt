package net.lpscream.weatherstudio.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import net.lpscream.weatherstudio.R
import net.lpscream.weatherstudio.adapters.LocationListAdapter
import net.lpscream.weatherstudio.databinding.LocationListFragmentBinding

class LocationListFragment : Fragment(), LocationListAdapter.ItemClickListener {

    private var _binding: LocationListFragmentBinding? = null
    private val binding get() = _binding!!
    private var countryItem: String = ""
    private val TAG = "LocationListFragment_log"
    private lateinit var adapterList: LocationListAdapter



    private lateinit var viewModel: LocationListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LocationListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, LocationListViewModelFactory(requireContext().getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE))).get(LocationListViewModel::class.java)



        binding.floatingActionButton.setOnClickListener {
            val requestKey = "COUNTRY_ITEM"
            setFragmentResultListener(requestKey){ requestKey, bundle ->
                countryItem = bundle.getString("country_item").toString()
                viewModel.getByName(countryItem)
            }
            val addCountry = AddCountryFragment()
            addCountry.show(requireActivity().supportFragmentManager, "countryPickerFragment")
        }

     viewModel.getCountryList().observe(viewLifecycleOwner, Observer {
         Log.d(TAG, "onActivityCreated: " + viewModel.getCountryList().value.toString())
         adapterList = LocationListAdapter(viewModel.getCountryList().value, this)
         binding.locationListRecyclerView.apply {
            setHasFixedSize(false)
             layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
             adapter = adapterList
         }
         adapterList.notifyDataSetChanged()
         Log.d(TAG, "onActivityCreated: recyclerview created")

     })
    }

    override fun onItemClick(position: Int) {
        val weatherFragment = WeatherFragment()
        val bundle = Bundle()
        bundle.putString("query", viewModel.getIdByName(viewModel.getCountryList().value?.get(position)
            .toString()))
        weatherFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.container, weatherFragment)
            .hide(this)
            .commit()
    }

    override fun onLongClick(position: Int) {
        viewModel.removePref(viewModel.getIdByName(viewModel.getCountryList().value?.get(position).toString()))
    }
}