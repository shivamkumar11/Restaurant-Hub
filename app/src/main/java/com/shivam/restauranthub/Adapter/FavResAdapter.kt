package com.shivam.restauranthub.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shivam.restauranthub.R
import com.shivam.restauranthub.database.RestaurantEntity
import com.squareup.picasso.Picasso


class FavResAdapter(
    private val context: Context,
    private val savedRestaurantList: List<RestaurantEntity>) : RecyclerView.Adapter<FavResAdapter.FavRestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavRestaurantViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.favourites_book_data, parent, false)

        return FavRestaurantViewHolder(view)

    }

    override fun getItemCount(): Int {
        return savedRestaurantList.size
    }

    override fun onBindViewHolder(holder: FavRestaurantViewHolder, position: Int) {
        val favRestaurant = savedRestaurantList.get(position)

        holder.favResName.text = favRestaurant.resName
        holder.favResPrice.text = favRestaurant.resPrice
        holder.favResRating.text = favRestaurant.resRating
        Picasso.get().load(favRestaurant.resImage).into(holder.favResImage)
    }


    class FavRestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var favResName: TextView = view.findViewById(R.id.favResName)
        val favResPrice: TextView = view.findViewById(R.id.favResPrice)
        val favResRating: TextView = view.findViewById(R.id.favResRating)
        val favResImage: ImageView = view.findViewById(R.id.favResImage)

    }
}