package com.example.dresstique_android.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dresstique_android.Clothes
import com.example.dresstique_android.MyAdapter
import com.example.dresstique_android.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {
    private lateinit var dbref : DatabaseReference
    private lateinit var clothesRecyclerview : RecyclerView
    private lateinit var clothesArrayList : ArrayList<Clothes>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        clothesRecyclerview = view.findViewById(R.id.eventsList)
        clothesRecyclerview.layoutManager = LinearLayoutManager(activity)
        clothesRecyclerview.setHasFixedSize(true)
        clothesArrayList = arrayListOf<Clothes>()
        getClothesData()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)?.visibility=View.VISIBLE
        return view
    }

    private fun getClothesData() {
        dbref = FirebaseDatabase.getInstance().getReference("clothes")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (clothesSnapshot in snapshot.children){
                        val clothes = clothesSnapshot.getValue(Clothes::class.java)
                        clothesArrayList.add(clothes!!)
                    }
                    clothesRecyclerview.adapter = MyAdapter(clothesArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                var errorDb = true;
            }
        })
    }

}