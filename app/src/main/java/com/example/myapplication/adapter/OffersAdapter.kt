package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ViewholderOffersBinding
import com.example.myapplication.model.ItemsModel

class OffersAdapter(val items:MutableList<ItemsModel>): RecyclerView.Adapter<OffersAdapter.OffersViewHolder>() {

    // Context
    private var context:Context?=null


    class OffersViewHolder(val binding:ViewholderOffersBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {
        context=parent.context
        val binding = ViewholderOffersBinding.inflate(LayoutInflater.from(context),parent,false)
        return OffersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {
        holder.binding.titleText.text = items[position].title
        holder.binding.priceText.text="$"+items[position].price.toString()

        Glide
            .with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener{

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}