package net.lpscream.weatherstudio.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.lpscream.weatherstudio.R
import net.lpscream.weatherstudio.databinding.WeatherListItemBinding
import java.text.SimpleDateFormat
import java.util.*

class WeatherListAdapter(private val context: Context, private var list: List<net.lpscream.weatherstudio.model.List>?):
    RecyclerView.Adapter<WeatherListAdapter.ItemViewHolder>() {

    var simpleDateFormat = SimpleDateFormat("hh:mm")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WeatherListItemBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.feelsLikeTextView.text =   context.resources.getString(R.string.feels_like) + list?.get(position)?.main?.feels_like
        holder.binding.humidityTextView.text =  context.resources.getString(R.string.humidity) + list?.get(position)?.main?.humidity
        holder.binding.pressureTextView.text =  context.resources.getString(R.string.pressure) + list?.get(position)?.main?.pressure
        holder.binding.tempTextView.text =  context.resources.getString(R.string.temp) + list?.get(position)?.main?.temp
        holder.binding.windSpeedTextView.text =  context.resources.getString(R.string.speed) + list?.get(position)?.wind?.speed
        holder.binding.dtTextView.text = simpleDateFormat.format(Date(list?.get(position)?.dt!!   * 1000L))
    }

    override fun getItemCount(): Int = list?.size!!



    inner class ItemViewHolder(val binding: WeatherListItemBinding): RecyclerView.ViewHolder(binding.root)

}