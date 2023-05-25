package com.example.dresstique_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.dresstique_android.databinding.ActivityMainBinding
import com.example.dresstique_android.fragments.HomeFragment
import com.example.dresstique_android.fragments.ProfileFragment
import com.example.dresstique_android.fragments.PromoFragment
import com.example.dresstique_android.fragments.SplashFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(SplashFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.promo -> replaceFragment(PromoFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                R.id.home -> replaceFragment(HomeFragment())

                else ->{



                }

            }

            true

        }
        binding.bottomNavigationView.visibility = View.GONE

    }

    fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()


    }
    }
