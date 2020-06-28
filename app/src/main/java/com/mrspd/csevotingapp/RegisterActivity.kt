package com.mrspd.csevotingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class RegisterActivity : AppCompatActivity() {
    var rollnumber: String = ""
    var password: String = ""
    var uid: String = ""
    lateinit var currentUser: FirebaseUser
    lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()

    }

    fun registerNumber(view: View) {
        rollnumber = etRollnumber.text.toString().trim()
        password = etPassword.text.toString().trim()
        if (rollnumber.isNotEmpty() && password.isNotEmpty()) {
            val webmail = "106119${rollnumber}@nitt.edu"
            registeruser(webmail, password)
        }
    }

    private fun registeruser(webmail: String, password: String) {
        progressbar1.visibility = View.VISIBLE
        mAuth.createUserWithEmailAndPassword(webmail, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    currentUser = FirebaseAuth.getInstance().currentUser!!
                    uid = currentUser!!.uid
                    mDatabase = FirebaseDatabase.getInstance().getReference("Users").child(uid)
                    val userMap = HashMap<String, String>()
                    userMap["name"] = rollnumber
                    userMap["password"] = password
                    mDatabase.setValue(userMap).addOnCompleteListener(OnCompleteListener { task ->
                        if (task.isComplete) {
                            sendVerificationLink()
                            Toast.makeText(this, " Creating database", Toast.LENGTH_SHORT).show()

                            Log.d("gghh", " created db")
                        } else
                            Log.d("gghh", "fail to create db")
                    })
                    Log.d("gghh", "success")
                } else {
                    Toast.makeText(this, "Password must atleast 6 characters", Toast.LENGTH_SHORT).show()
                    progressbar1.visibility = View.INVISIBLE

                    Log.w("gghh", "failure")
                }
            }
    }

    private fun sendVerificationLink() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.sendEmailVerification()?.addOnCompleteListener {
            Log.d("gghh", "createUserWithEmail: email verification sent")
            mDatabase =
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("verified")
            mDatabase.setValue("no").addOnCompleteListener(OnCompleteListener { task ->
                if (task.isComplete) {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()

                    d("gghh", "created verified child successfully")
                    val intent = Intent(this, VerifyEmailActivity::class.java)
                    val bundle = Bundle()
                    //    intent.putExtra("currentuser", currentUser)
                    startActivity(intent)
                    finish()
                } else {
                    progressbar1.visibility = View.INVISIBLE
                    Toast.makeText(this, " failed error 4", Toast.LENGTH_SHORT).show()
                    d("gghh", "creating verified child failed")
                }
            })

        }
    }
}