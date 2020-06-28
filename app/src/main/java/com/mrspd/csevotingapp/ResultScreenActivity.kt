package com.mrspd.csevotingapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mrspd.csevotingapp.adapter.CustomListAdapterResultsStats
import com.mrspd.csevotingapp.model.VoteStats
import kotlinx.android.synthetic.main.activity_result_screen.*

class ResultScreenActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference
    var nameList = ArrayList<VoteStats>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screen)
        val b = intent.extras
        val votename = b?.getString("votename")
        loadResults(votename)
    }
    private fun loadResults(votename: String?) {
        mDatabase = FirebaseDatabase.getInstance().getReference("Votes").child(votename.toString())
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var winnername:String = ""
                var highestcount : Int = 0
                nameList.clear()
                for (dsp in dataSnapshot.getChildren()) {
                    val nameOfCandidate = dsp.key.toString()
                    val size = dsp.childrenCount.toInt()
                    if (size > highestcount){
                        highestcount = size
                        winnername = nameOfCandidate
                    }
                    val voteStats = VoteStats(nameOfCandidate,size)
                    nameList.add(voteStats)
                }
                Log.d("gghh", "success")
                showListUi(nameList,winnername,votename)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("gghh", "failed")
            }
        })
    }

    private fun showListUi(
        votelists: java.util.ArrayList<VoteStats>,
        winnername: String,
        votename: String?
    ) {
        val customListAdapter = CustomListAdapterResultsStats(this, votelists)
        tvWinner.text = winnername
        tvResultOf.text = votename
        lvResultStats.apply {
            adapter = customListAdapter
            customListAdapter.notifyDataSetChanged()
        }
    }}