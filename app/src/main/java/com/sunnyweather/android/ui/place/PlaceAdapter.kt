package com.sunnyweather.android.ui.place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.databinding.PlaceItemBinding
import com.sunnyweather.android.logic.model.Place

class PlaceAdapter(private val fragment: Fragment, private val placeList: List<Place>) :
        RecyclerView.Adapter<PlaceAdapter.ViewHolder>(){

     class ViewHolder(binding: PlaceItemBinding) : RecyclerView.ViewHolder(binding.root) {
         val placeName = binding.placeName
         val placeAddress = binding.placeAddress
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PlaceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.placeName.text = place.name
        holder.placeAddress.text = place.address
    }

    override fun getItemCount() = placeList.size

}