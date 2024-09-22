package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewholderCategoryBinding
import com.example.myapplication.model.CategoryModel

class CategoryAdapter(val items:MutableList<CategoryModel>):
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

        // Class Parameters
        private lateinit var context:Context
        private var selectedPosition=-1
        private var lastSelectedPosition=-1


    class ViewHolder(val binding:ViewholderCategoryBinding):RecyclerView.ViewHolder(binding.root)

    // implement Metods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding= ViewholderCategoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = items[position]
        holder.binding.titleCategory.text=items.title


        holder.binding.root.setOnClickListener{
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if(selectedPosition==position){
            holder.binding.titleCategory.setBackgroundResource(R.drawable.orange_bg)

        }else{
            holder.binding.titleCategory.setBackgroundResource(R.drawable.edittext_bg)

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}