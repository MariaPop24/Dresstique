package com.example.dresstique_android.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.dresstique_android.MainActivity
import com.example.dresstique_android.R
import com.example.dresstique_android.databinding.FragmentLogInBinding
import com.example.dresstique_android.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        auth = FirebaseAuth.getInstance()

        val emailInput = view.findViewById<EditText>(R.id.editTextEmail)
        val passwordInput = view.findViewById<EditText>(R.id.editTextPassword)
        val signupBtn = view.findViewById<Button>(R.id.buttonSignUp)
        val loginBtn = view.findViewById<Button>(R.id.buttonBackLogIn)

        signupBtn.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if(email.isEmpty() || password.isEmpty()) {
                Toast.makeText(activity, "All fields are required", Toast.LENGTH_LONG).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful) {
                            Toast.makeText(activity, "Account created successfully!", Toast.LENGTH_SHORT).show()
                            (activity as? MainActivity)?.replaceFragment(HomeFragment())
                        } else {
                            Toast.makeText(activity, "Something went wrong! Try again later", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        loginBtn.setOnClickListener {
            (activity as? MainActivity)?.replaceFragment(LogInFragment())
        }
        return view
    }
}