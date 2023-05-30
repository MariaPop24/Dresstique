package com.example.dresstique_android.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.dresstique_android.MainActivity
import com.example.dresstique_android.R
import com.example.dresstique_android.databinding.FragmentLogInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class LogInFragment : Fragment() {
    companion object {
        private const val RC_SIGN_IN = 9001
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_log_in, container, false)

        auth = FirebaseAuth.getInstance()

        val emailInput: EditText = view.findViewById(R.id.editTextEmail)
        val passwordInput: EditText = view.findViewById(R.id.editTextPassword)
        val signinBtn: Button = view.findViewById(R.id.buttonSignIn)
        val googleSigninBtn: Button = view.findViewById(R.id.buttonSignInGoogle)
        val signupBtn: Button = view.findViewById(R.id.buttonSignUp)

        signinBtn.setOnClickListener {
            val email = emailInput.text.toString()
            val password =  passwordInput.text.toString()
            signIn(email, password)
        }

        signupBtn.setOnClickListener {
            (activity as? MainActivity)?.replaceFragment(SignUpFragment())
        }

        val googleSignInAction = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.google_default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), googleSignInAction)

        googleSigninBtn.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        return view
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = auth.currentUser
                    Toast.makeText(activity, "Login successful!", Toast.LENGTH_SHORT).show()
                    (activity as? MainActivity)?.replaceFragment(HomeFragment())
                } else {
                    Toast.makeText(activity, "Incorrect email or password!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    (activity as? MainActivity)?.replaceFragment(HomeFragment())
                } else {
                    (activity as? MainActivity)?.replaceFragment(HomeFragment())
                    Toast.makeText(requireContext(), "Authentication Failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                (activity as? MainActivity)?.replaceFragment(HomeFragment())
            }
        }
    }

}