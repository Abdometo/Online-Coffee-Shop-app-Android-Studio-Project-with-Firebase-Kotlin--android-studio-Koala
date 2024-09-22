package com.example.myapplication.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.RoundedCorner
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.adapter.SizeAdapter
import com.example.myapplication.databinding.ActivityDetailsBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.ItemsModel
import com.example.project1762.Helper.ManagmentCart

class DetailsActivity : BaseActivity() {
    // Binding
    lateinit var binding: ActivityDetailsBinding

    // Item
    private lateinit var item:ItemsModel

    // Management Cart
    private lateinit var managementCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        managementCart = ManagmentCart(this)


        bundle()
        initListSize()
    }

    private fun initListSize() {
        val sizeList = ArrayList<String>()
        sizeList.add("1")
        sizeList.add("2")
        sizeList.add("3")
        sizeList.add("4")


        binding.sizeList.adapter = SizeAdapter(sizeList)
        binding.sizeList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        val colorList = ArrayList<String>()

        for(imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }

        Glide.with(this)
            .load(colorList[0])
            .apply(RequestOptions.bitmapTransform(RoundedCorners(100)))
            .into(binding.picMain)


    }

    private fun bundle() {

        binding.apply {

            item = intent.getParcelableExtra("object")!!
            titleText.text = item.title
            descriptionText.text=item.description
            priceText.text="$"+item.price
            ratingBar.rating=item.rating.toFloat()


            addToCartButton.setOnClickListener{

                item.numberInCart=Integer.valueOf(numberItemText.text.toString())

                managementCart.insertItems(item)
                
            }


            backButton.setOnClickListener {
                startActivity(Intent(this@DetailsActivity,MainActivity::class.java))
            }

            plusButton.setOnClickListener {
                numberItemText.text=(item.numberInCart+1).toString()
                item.numberInCart++
            }

            minusButton.setOnClickListener {

                if(item.numberInCart>0){
                numberItemText.text=(item.numberInCart-1).toString()
                item.numberInCart--
              }
            }


        }
    }
}