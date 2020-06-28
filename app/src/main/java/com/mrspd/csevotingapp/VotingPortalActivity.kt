package com.mrspd.csevotingapp

import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.mrspd.csevotingapp.model.VoteNames
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_voting_portal.*
import kotlinx.android.synthetic.main.activity_voting_portal.tvQuestion


class VotingPortalActivity : AppCompatActivity() {
    lateinit var currentUser: FirebaseUser
    val myvote: VoteNames? = null
    lateinit var mAuth: FirebaseAuth
  lateinit var name: String
    lateinit var mDatabase1: DatabaseReference
    lateinit var mDatabase2: DatabaseReference
    lateinit var mDatabase3: DatabaseReference
    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voting_portal)
        radioGroup = findViewById(R.id.radioGroup)
        mAuth = FirebaseAuth.getInstance()
        val b = intent.extras
        val myvote: VoteNames? = b?.getParcelable("myvote")
        d("gghh", myvote?.name.toString())
        myvote?.name?.let {
            name = it
        }
        checkWetherUserHasVoted(mAuth,myvote)


    }

    private fun checkWetherUserHasVoted(
        mAuth: FirebaseAuth,
        myvote: VoteNames?
    ) {
        mDatabase3 = FirebaseDatabase.getInstance().getReference("Users")
            .child(mAuth.currentUser?.uid.toString())
        mDatabase3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val voted =
                    dataSnapshot.child("votedfor").child(name).getValue(String::class.java)
                if (voted == "Voted Successfully") {
                    Toast.makeText(
                        this@VotingPortalActivity,
                        "Your have already voted!!",
                        Toast.LENGTH_SHORT
                    ).show()
                    d("gghh", "Your have already voted!!")
                    finish()
                } else {
                    doRest(myvote)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Toast.makeText(
                    this@VotingPortalActivity,
                    "Something went wrong Error code 3672",
                    Toast.LENGTH_SHORT
                ).show()
                d("gghh", "Something went wrong Error code 3672")
                finish()
            }
        })
    }

    private fun doRest(myvote: VoteNames?) {
        d("gghh",  myvote?.option1)
        myvote?.description?.let {
            tvQuestion.text = it
        }
        if (!myvote?.option1.equals("")) {
            rdOption1.visibility = View.VISIBLE
            rdOption1.text = myvote?.option1.toString()
        }
        if (!myvote?.option2.equals("")) {
            rdOption2.visibility = View.VISIBLE

            rdOption2.text = myvote?.option2.toString()
        }
        if (!myvote?.option3.equals("")) {
            rdOption3.visibility = View.VISIBLE

            rdOption3.text = myvote?.option3.toString()
        }
        if (!myvote?.option4.equals("")) {
            rdOption4.visibility = View.VISIBLE

            rdOption4.text = myvote?.option4.toString()
        }
        if (!myvote?.option5.equals("")) {
            rdOption5.visibility = View.VISIBLE

            rdOption5.text = myvote?.option5.toString()
        }
        if (!myvote?.option6.equals("")) {
            rdOption6.visibility = View.VISIBLE

            rdOption6.text = myvote?.option6.toString()
        }
        if (!myvote?.option7.equals("")) {
            rdOption7.visibility = View.VISIBLE

            rdOption7.text = myvote?.option7.toString()
        }
        if (!myvote?.option8.equals("")) {
            rdOption8.visibility = View.VISIBLE

            rdOption8.text = myvote?.option8.toString()
        }
        if (!myvote?.option9.equals("")) {
            rdOption9.visibility = View.VISIBLE

            rdOption9.text = myvote?.option9.toString()
        }
        if (!myvote?.option10.equals("")) {
            rdOption10.visibility = View.VISIBLE

            rdOption10.text = myvote?.option10.toString()
        }

    }

    fun submiVote(view: View) {
        val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
        radioButton = findViewById(intSelectButton)
        mDatabase1 =
            FirebaseDatabase.getInstance().getReference("Votes")
                .child(name).child(radioButton.text.toString())
        mDatabase1.child(mAuth.currentUser?.uid.toString()).setValue(mAuth.currentUser?.uid)
            .addOnCompleteListener(OnCompleteListener { task ->
                if (task.isComplete) {
                    Toast.makeText(this, "Your has been submitted successfully", Toast.LENGTH_SHORT)
                        .show()
                    d("gghh", "Your vote has been submitted securely ")
                    setUserHasVoted()
                    finish()
                } else {
                    progressbar1.visibility = View.INVISIBLE
                    Toast.makeText(
                        this,
                        " Sorry something got wrong please contact admin",
                        Toast.LENGTH_SHORT
                    ).show()
                    d("gghh", "Sorry something got wrong please contact admin")
                    finish()
                }
            })
    }

    private fun setUserHasVoted() {
        mDatabase2 = FirebaseDatabase.getInstance().getReference("Users")
            .child(mAuth.currentUser?.uid.toString()).child("votedfor")
            .child(name)
        mDatabase2.setValue("Voted Successfully").addOnCompleteListener(OnCompleteListener { task ->
            if (task.isComplete) {
                finish()
                Toast.makeText(this, " Vote Status Changed", Toast.LENGTH_SHORT).show()

                d("gghh", "Vote Status Changed")
            } else
                d("gghh", "failed to change Vote Status ")
        })

    }
}