package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.databinding.ViewholderCartBinding
import com.example.myapplication.helper.ChangeNumberItemsListener
import com.example.myapplication.model.ItemsModel
import com.example.project1762.Helper.ManagmentCart

class CartAdapter(
    private val listItemSelected:ArrayList<ItemsModel>,
    context:Context,
    var changedNumberItemListener:ChangeNumberItemsListener?=null
    ):RecyclerView.Adapter<CartAdapter.CartViewHolder>() {




    inner class CartViewHolder(val binding:ViewholderCartBinding):RecyclerView.ViewHolder(binding.root)


    private val managementCart = ManagmentCart(context)



    // implement methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = listItemSelected[position]


        holder.binding.titleText3.text = item.title
        holder.binding.feeEachItem.text = "$${item.price}"
        holder.binding.totalEachItem.text = "$${Math.round(item.numberInCart*item.price)}"
        holder.binding.numberItemText.text = item.numberInCart.toString()


        Glide
            .with(holder.itemView.context)
            .load(item.picUrl)
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.picCart)


        holder.binding.plusCartButton.setOnClickListener{
            managementCart.plusItem(listItemSelected,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changedNumberItemListener?.onChanged()
                }

            })
        }


        holder.binding.minusCartButton.setOnClickListener{
            managementCart.minusItem(listItemSelected,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changedNumberItemListener?.onChanged()
                }

            })
        }
    }

    override fun getItemCount(): Int {
        return listItemSelected.size
    }
}