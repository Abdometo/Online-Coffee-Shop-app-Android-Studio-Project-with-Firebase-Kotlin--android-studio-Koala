package com.example.myapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Activity.DetailsActivity
import com.example.myapplication.databinding.ViewholderPopularBinding
import com.example.myapplication.model.ItemsModel

class PopularAdapter(val items:MutableList<ItemsModel>): RecyclerView.Adapter<PopularAdapter.ItemsViewHolder>() {




    private var context:Context?=null
    class ItemsViewHolder(val binding:ViewholderPopularBinding):RecyclerView.ViewHolder(binding.root)

    // Implement Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        context = parent.context
        val binding  =  ViewholderPopularBinding.inflate(LayoutInflater.from(context),parent,false)
        return ItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.binding.titleText.text = items[position].title
        holder.binding.priceText.text="$"+items[position].price.toString()
        holder.binding.ratingBar.rating = items[position].rating.toFloat()
        holder.binding.extraText.text = items[position].extra


        Glide
            .with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .into(holder.binding.pic)


        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,DetailsActivity::class.java)
            intent.putExtra("object",items[position])
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}