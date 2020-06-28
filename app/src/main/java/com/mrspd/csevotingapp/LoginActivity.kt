package com.mrspd.csevotingapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*


class LoginActivity : AppCompatActivity() {
    var rollnumber: String = ""
    var password: String = ""
    var uid: String = ""
    lateinit var currentUser: FirebaseUser
    lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()


    }

    fun signIn(webmail: String, password: String) {
        mAuth.signInWithEmailAndPassword(webmail, password)
            .addOnCompleteListener(this,
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        val user: FirebaseUser? = mAuth.getCurrentUser()
                        progressbar12.visibility = View.INVISIBLE

                        Toast.makeText(
                            this@LoginActivity, "Authentication Successs!!!",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()

                    } else {
                        progressbar12.visibility = View.INVISIBLE

                        Toast.makeText(
                            this@LoginActivity, "Authentication failed!!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
    }
    fun goToRegister(view: View) {
        startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
    }

    fun loginUser(view: View) {
        rollnumber = etRollnumber1.text.toString().trim()
        password = etPassword1.text.toString().trim()
        if (rollnumber.isNotEmpty() && password.isNotEmpty()) {
            progressbar12.visibility = View.VISIBLE
            val webmail = "${rollnumber}@nitt.edu"
            signIn(webmail, password)
        }
    }
}