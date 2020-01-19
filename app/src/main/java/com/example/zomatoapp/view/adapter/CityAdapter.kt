package com.example.zomatoapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.example.zomatoapp.R
import com.example.zomatoapp.data.model.cities.City
import com.example.zomatoapp.helpers.requestOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_city_search.view.*


class CityAdapter constructor(val context: Context, val data: List<City>) :
    RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    companion object {
        public const val TAG = "CityAdapter"

    }

    var cityAdapterCallback: CityAdapterCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_city_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = data[position]

        holder.itemView.cityNameTextView.text = city.name
        holder.itemView.countryNameTextView.text = city.countryName

        Glide.with(context)
            .load(city.countryFlagUrl)
            .apply(requestOptions)
            .signature(ObjectKey(city.countryId!!))
            .into(holder.itemView.countryIconImageView);

        holder.itemView.setOnClickListener {
            cityAdapterCallback?.citySelected(position, city)
        }
    }


    override fun getItemCount(): Int = data.size

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer


    interface CityAdapterCallback {
        fun citySelected(position: Int, city: City)
    }
}