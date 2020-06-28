package com.mrspd.csevotingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth


class VerifyEmailActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_email)
        val intent: Intent = intent
//        currentUser = intent.getParcelableExtra("currentuser")
        mAuth = FirebaseAuth.getInstance()
    }

    fun refresh(view: View) {
        var emailVerification: Boolean? = false
        val usertask = mAuth.currentUser!!.reload()
        usertask.addOnSuccessListener(object : OnSuccessListener<Any?> {
            override fun onSuccess(p0: Any?) {
                val user = mAuth.currentUser
                emailVerification = user?.isEmailVerified()
                checkVErification(emailVerification)
            }
        })
        checkVErification(emailVerification)

    }

    private fun checkVErification(emailVerification: Boolean?) {
        if (emailVerification!!) {
            Toast.makeText(this," Email Verified", Toast.LENGTH_SHORT).show()

            Log.w("gghh", "verified  :: $emailVerification")
            val intent = Intent(this@VerifyEmailActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this," please verify email",Toast.LENGTH_SHORT).show()

            Log.w("gghh", "please verify. $emailVerification and user is ${mAuth.currentUser?.uid}")
        }
    }
}