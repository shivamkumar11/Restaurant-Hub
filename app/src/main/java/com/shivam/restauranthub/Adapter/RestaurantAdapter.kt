package com.shivam.restauranthub.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.shivam.restauranthub.DoAsyncTask
import com.shivam.restauranthub.R
import com.shivam.restauranthub.database.RestaurantEntity
import com.squareup.picasso.Picasso


class RestaurantAdapter(private val context: Context, private val data: ArrayList<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {
    var count = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.home_data, parent, false)
        return RestaurantViewHolder(view)

    }


    override fun getItemCount(): Int {
        return data.size

    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {


        val datas = data[position]

        holder.resName.text = datas.resName
        //holder.resImage = datas.resImage
        Picasso.get().load(datas.resImage).into(holder.resImage)
        holder.resPrice.text = "Rs:${datas.resPrice}"
        holder.resRating.text = datas.resRating


        val id = datas.resId
        val name = datas.resName
        val price = datas.resPrice
        val image = datas.resImage
        val rating = datas.resRating


        val restaurantEntity = RestaurantEntity(
            id.toInt(),
            name.toString(),
            price.toString(),
            image.toString(),
            rating.toString()
        )

        val checkFav = DoAsyncTask(context, restaurantEntity, 1).execute()
        val isfav = checkFav.get()
        if (isfav) {
            count = count + 1
            holder.resStar.setImageResource(R.drawable.added_to_fav)
            // Toast.makeText(context,"Yeh tumne favourite me dala hai",Toast.LENGTH_SHORT).show()

        } else {
            holder.resStar.setImageResource(R.drawable.not_added_to_fav)
            //   Toast.makeText(context,"Yeh abhi add nahi hua hai",Toast.LENGTH_SHORT).show()
        }
        //     val a = Intent(context,BookDetailActivity::class.java)
        //  a.putExtra("book_id",books.bookId)
        //  context.startActivity(a)
        //   when (position) {


        holder.itemView.setOnClickListener {
            if (!DoAsyncTask(context, restaurantEntity, 1).execute().get()) {

                // for save book we execute insert mode 2 method here
                val async = DoAsyncTask(context, restaurantEntity, 2).execute()
                val result = async.get()
                if (result) {
                    // yeadi book add ho gyi to remove button show kro otherwise error show kro
                    Toast.makeText(context, "Restaurant added successfully...", Toast.LENGTH_SHORT)
                        .show()
                    holder.resStar.setImageResource(R.drawable.added_to_fav)

                } else {
                    Toast.makeText(context, "Something error occured...", Toast.LENGTH_SHORT).show()

                }

            } else {
                // yeh block jab execute hoga jab book favourites me hai tab
                val async = DoAsyncTask(context, restaurantEntity, 3).execute()
                val result = async.get()
                if (result) {
                    Toast.makeText(context, "Restaurant remove from favourites", Toast.LENGTH_SHORT)
                        .show()
                    holder.resStar.setImageResource(R.drawable.not_added_to_fav)

                } else {
                    Toast.makeText(context, "Some error occured..", Toast.LENGTH_SHORT).show()

                }


            }
            /*  Toast.makeText(context, "hi$position \n" +
                      "id= $id\n" +
                      "name= $name\n" +
                      "price= $price\n" +
                      "image= $image\n" +
                      "rating= $rating\n", Toast.LENGTH_SHORT).show() */
        }


    }


    class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var resName: TextView = view.findViewById(R.id.resName)
        var resPrice: TextView = view.findViewById(R.id.resPrice)
        var resImage: ImageView = view.findViewById(R.id.resImage)
        var resRating: TextView = view.findViewById(R.id.resRatings)
        var resStar: ImageView = view.findViewById(R.id.resStar)


    }


}



