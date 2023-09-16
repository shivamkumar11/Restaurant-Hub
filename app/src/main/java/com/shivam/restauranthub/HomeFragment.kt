package com.shivam.restauranthub

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.shivam.restauranthub.Adapter.Restaurant
import com.shivam.restauranthub.Adapter.RestaurantAdapter
import com.shivam.restauranthub.database.RestaurantEntity
import com.shivam.restauranthub.db.RestaurantDatabase
import com.shivam.restauranthub.util.ConnectionManager
import org.json.JSONException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var recyclerView: RecyclerView;
lateinit var layoutManager: LinearLayoutManager;

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recycler2)

        // Inflate the layout for this fragment
        val data = arrayListOf<Restaurant>()
        /*   Restaurant("0","A","1.1","100","d"),
           Restaurant("1","B","1.2","200","d"),
           Restaurant("2","C","1.3","300","d"),
           Restaurant("3","D","1.4","400","d"),
           Restaurant("4","E","1.5","500","d"),
           Restaurant("5","F","1.6","600","d"),
           Restaurant("6","g","1.7","700","d"),

         )*/

        if (ConnectionManager().isDeviceNetworkConnected(activity as Context)) {


            try {
                val queue = Volley.newRequestQueue(activity as Context)
                val url = "http://13.235.250.119/v2/restaurants/fetch_result/"

                val jsonObjectRequest =
                    object : JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                        // first here we get the json object then 2-
                        val dataObject = it.getJSONObject("data")
                        // here 2nd me hmne us jsonobject se getString e us string ki valu nikala 3-
                        val success = dataObject.getString("success")
                        // 3rd- fir yaha us string value ko cast kiya boolean me fir 4-
                        if (success.toBoolean()) {
                            //4th fir value true hone pe hmne us jsonobject se uske andar ka jsonarray object nikala 5-
                            val jsonArrayData = dataObject.getJSONArray("data")
                            // 5th- fir yaha pe us json array object ko one by one its value nikala
                            for (i in 0 until jsonArrayData.length()) {

                                val restaurantArrayObject = jsonArrayData.getJSONObject(i)
                                val restaurantObject = Restaurant(
                                    restaurantArrayObject.getString("id"),
                                    restaurantArrayObject.getString("name"),
                                    restaurantArrayObject.getString("rating"),
                                    restaurantArrayObject.getString("cost_for_one"),
                                    restaurantArrayObject.getString("image_url")


                                )
                                /*    val id = restaurantArrayObject.getString("id")
                                val restaurantEntity = RestaurantEntity(
                                    resName = restaurantArrayObject.getString("name"),
                                    resId = id.toInt(),
                                    resPrice = restaurantArrayObject.getString("cost_for_one"),
                                    resImage = restaurantArrayObject.getString("image_url"),
                                    resRating = restaurantArrayObject.getString("rating")
                                )

                                val checkFav =
                                    DoAsyncTask(activity as Context, restaurantEntity, 1).execute()
                                val isFav = che


                                ckFav.get()

                                val a= Intent()
                                a.putExtra("isFav",isFav)
                                startActivity(a)
                                if (isFav) {
                                    // star unhilighted
                                    val favColor =
                                        ContextCompat.getColor(activity as Context, R.color.black)

                                } else {
                                    // highlight star color
                                } */


                                //   println(restaurantObject)

                                data.add(restaurantObject)
                                layoutManager = LinearLayoutManager(activity as Context)
                                val adapter = RestaurantAdapter(activity as Context, data)
                                recyclerView.layoutManager = layoutManager
                                recyclerView.adapter = adapter


                            }


                        } else {
                            Toast.makeText(
                                context,
                                "Error occoured to retrieving data..",
                                Toast.LENGTH_SHORT
                            ).show()
                            //  println("Error to retrieving data..")
                        }
                        println("Response is $it")


                    }, Response.ErrorListener {
                        if (activity != null) {
                            Toast.makeText(context, "Volley error occured...", Toast.LENGTH_SHORT)
                                .show()
                            //  println("Error to retrieving data..")
                            println("Error is $it")
                        }

                    }) {
                        override fun getHeaders(): MutableMap<String, String> {
                            val headers = HashMap<String, String>()
                            headers["Content-type"] = "application/json"
                            headers["token"] = "9bf534118365f1"

                            return headers
                        }
                    }
                queue.add(jsonObjectRequest)
            } catch (e: JSONException) {
                Toast.makeText(context, "Wait..", Toast.LENGTH_SHORT).show()

            }
            layoutManager = LinearLayoutManager(activity as Context)
            val adapter = RestaurantAdapter(activity as Context, data)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
        } else {
            Toast.makeText(
                activity as Context,
                "Plz connect your internet,\n and restart app..",
                Toast.LENGTH_SHORT
            ).show()
        }





        return view;
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

class DoAsyncTask(val context: Context, val restaurantEntity: RestaurantEntity, val mode: Int) :
    AsyncTask<Void, Void, Boolean>() {
    /*
    Mode 1 check db if book is favourites or not
    mode 2 check book into db as favourites
    Mode 3 remove book from the database

     */
    // room class databasebuilder method(context,class javaversion responsible for creation of db,database name)

    val db = Room.databaseBuilder(context, RestaurantDatabase::class.java, "restaurant-db").build()


    override fun doInBackground(vararg params: Void?): Boolean {
        when (mode) {
            1 -> {
                // check db if book is favourites or not
                val restaurant: RestaurantEntity? =
                    db.restaurantDao().getRestaurantById(restaurantEntity.resId.toString())
                db.close()
                return restaurant != null


            }
            2 -> {
                // save the book into db as favourites
                db.restaurantDao().insertRestaurant(restaurantEntity)
                db.close()
                return true

            }
            3 -> {
                // remove the favourites book
                db.restaurantDao().deleteRestaurant(restaurantEntity)
                db.close()
                return true


            }
        }


        return false
    }

}