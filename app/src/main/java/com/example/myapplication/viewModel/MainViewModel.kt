package com.example.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.CategoryModel
import com.example.myapplication.model.ItemsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Locale.Category

class MainViewModel: ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance();


    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    private val _popular = MutableLiveData<MutableList<ItemsModel>>()
    private val _offers = MutableLiveData<MutableList<ItemsModel>>()


    val category:LiveData<MutableList<CategoryModel>> = _category
    val popular:LiveData<MutableList<ItemsModel>> = _popular
    val offers:LiveData<MutableList<ItemsModel>> = _offers

    // Load Caegory Finction
    fun loadCategory(){
        val Ref = firebaseDatabase.getReference("Category")

        Ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val lists = mutableListOf<CategoryModel>()

                for(childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(CategoryModel::class.java)

                    if(list!=null){
                        lists.add(list)
                    }
                }

                _category.value = lists

             }

            override fun onCancelled(error: DatabaseError) {
             }

        })
    }

    fun loadPopular(){

        val Ref =  firebaseDatabase.getReference("Items")

        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()

                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(ItemsModel::class.java)

                    if (list != null) {
                        lists.add(list)
                    }
                }

                _popular.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }



    // load offers
    fun loadOffers(){
        val Ref =  firebaseDatabase.getReference("Offers")

        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()

                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(ItemsModel::class.java)

                    if (list != null) {
                        lists.add(list)
                    }
                }

                _offers.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }




}