package com.example.dresstique_android.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dresstique_android.MainActivity
import com.example.dresstique_android.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonSignIn.setOnClickListener {
//            (activity as? MainActivity)?.replaceFragment(SignUpFragment())
//        }
    }
}