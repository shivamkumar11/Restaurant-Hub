package com.shivam.restauranthub

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivam.restauranthub.Adapter.Question

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FaqsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FaqsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var recyclerView: RecyclerView;
    lateinit var layoutManager: LinearLayoutManager;

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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_faqs, container, false)
        val data = arrayListOf<Question>(
            Question("Q1.How can I check favourites dish?", "Go to favourites tab in left side menu."),
            Question("Q2.How to save favourites dish?", "Click to heart icon to save it successfully.."),
            Question("Q3.How to update profile?", "Edit and save detail from profile section."),
            Question("Q4.How to logout my account?", "Click on left side logout exit button"),
            Question("Q5.How to contact us?", "mail@gmail.com"))
        recyclerView = view.findViewById(R.id.recyclerview3)
        layoutManager = LinearLayoutManager(activity as Context)
        val adapter = QuestionAdapter(activity as Context, data)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter



        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FaqsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FaqsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}