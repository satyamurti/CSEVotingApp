package com.mrspd.csevotingapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mAuth = FirebaseAuth.getInstance()

        checkWetherUserVerified(mAuth)
        navView.setupWithNavController(fragmentHostForNav.findNavController())
    }

    private fun checkWetherUserVerified(mAuth: FirebaseAuth) {
        if (!(mAuth.currentUser?.isEmailVerified)!!){
          startActivity(Intent(this,VerifyEmailActivity::class.java))
            finish()
        }
    }


}