package net.lpscream.weatherstudio.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.lpscream.weatherstudio.databinding.ListItemBinding

class LocationListAdapter(private var list: List<String>?,
                          val mItemClickListener:ItemClickListener):
    RecyclerView.Adapter<LocationListAdapter.ItemViewHolder>() {
    private val TAG = "LocationListAdapter_log"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.countryName.text = list?.get(position)
    }

    override fun getItemCount(): Int = list?.size!!



    inner class ItemViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.countryName.setOnClickListener {
                mItemClickListener.onItemClick(adapterPosition) }
            binding.countryName.setOnLongClickListener {
                mItemClickListener.onLongClick(adapterPosition)
                return@setOnLongClickListener true}
        }

    }





    interface ItemClickListener{
        fun onItemClick(position: Int)
        fun onLongClick(position: Int)
    }
}



