package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewholderSizeBinding
import com.example.myapplication.model.ItemsModel

class SizeAdapter(val items:MutableList<String>): RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context



    // View Holder
    class SizeViewHolder(val binding:ViewholderSizeBinding): RecyclerView.ViewHolder(binding.root) {

    }


    // Implement Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        context = parent.context
        val binding  = ViewholderSizeBinding.inflate(LayoutInflater.from(context),parent,false)
        return SizeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if(selectedPosition==position){
            holder.binding.img.setBackgroundResource(R.drawable.orange_bg)
        }else{
            holder.binding.img.setBackgroundResource(R.drawable.size_bg)
        }

        val imageSize = when(position){
            0->45.dpToPx(context)
            1->50.dpToPx(context)
            2->55.dpToPx(context)
            3->65.dpToPx(context)
            else->70.dpToPx(context)
        }

        val layoutParams = holder.binding.img.layoutParams
        layoutParams.width=imageSize
        layoutParams.height=imageSize
        holder.binding.img.layoutParams=layoutParams

    }


    // dp to Px
    fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }
    override fun getItemCount(): Int {
        return items.size

    }
}