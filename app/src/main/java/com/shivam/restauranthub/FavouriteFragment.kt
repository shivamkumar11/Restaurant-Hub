package com.shivam.restauranthub

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.shivam.restauranthub.Adapter.FavResAdapter
import com.shivam.restauranthub.database.RestaurantEntity
import com.shivam.restauranthub.db.RestaurantDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavouriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouriteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var recyclerView: RecyclerView;
    lateinit var layoutManager: GridLayoutManager;

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


        val view = inflater.inflate(R.layout.fragment_favourite, container, false)
        recyclerView = view.findViewById(R.id.recyclerview3)
        // Inflate the layout for this fragment
        val savedRestaurantList = RetreiveFavAsyscBook(activity as Context).execute().get()

        if (savedRestaurantList != null && activity != null) {
            //  progressLayout.visibility=View.GONE
            //  progressBar.visibility=View.GONE
            layoutManager = GridLayoutManager(activity as Context, 2)
            recyclerView.layoutManager = layoutManager
            val adapter = FavResAdapter(activity as Context, savedRestaurantList)
            recyclerView.adapter = adapter

        }
        return view
    }

    class RetreiveFavAsyscBook(val context: Context) :
        AsyncTask<Void, Void, List<RestaurantEntity>>() {
        override fun doInBackground(vararg params: Void?): List<RestaurantEntity> {
            val db = Room.databaseBuilder(context, RestaurantDatabase::class.java, "restaurant-db")
                .build()
            return db.restaurantDao().getAllRestaurants()


        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavouriteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavouriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}