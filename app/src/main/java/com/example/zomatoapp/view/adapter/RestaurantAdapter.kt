package com.example.zomatoapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zomatoapp.R
import com.example.zomatoapp.data.model.restaurants.Restaurant
import com.example.zomatoapp.helpers.requestOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_restaurant.view.*


class RestaurantAdapter constructor(val context: Context, val data: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    companion object {
        public const val TAG = "RestaurantAdapter"

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_restaurant, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = data[position]

        holder.itemView.tvRestaurantName.text = restaurant.name
        holder.itemView.tvRestaurantDesc.text = restaurant.cuisines

        Glide.with(context)
            .load(restaurant.thumb)
            .apply(requestOptions)
            .placeholder(R.drawable.ic_food)
            .into(holder.itemView.ivRestaurant)

    }


    override fun getItemCount(): Int = data.size

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer

}