package com.shivam.restauranthub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shivam.restauranthub.Adapter.Question

class QuestionAdapter(val context: Context,val data:ArrayList<Question>):RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {



    class QuestionViewHolder(view: View):RecyclerView.ViewHolder(view){
        val question:TextView=view.findViewById(R.id.question)
        val answer:TextView=view.findViewById(R.id.answer)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.faq_data, parent, false)
        return QuestionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val datas=data[position]
        holder.question.text=datas.question
        holder.answer.text=datas.answer



    }

}