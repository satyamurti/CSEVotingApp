package com.mrspd.csevotingapp.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mrspd.csevotingapp.HomeActivity
import com.mrspd.csevotingapp.R
import com.mrspd.csevotingapp.adapter.CustomListAdapter
import com.mrspd.csevotingapp.model.VoteNames
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference
    var votelists = ArrayList<VoteNames>()
    lateinit var vote: VoteNames
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadVotes()
    }

    private fun loadVotes() {
        progressbar1234.visibility= View.VISIBLE
        mDatabase = FirebaseDatabase.getInstance().getReference("VotingSystem")
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                votelists.clear()
                for (dsp in dataSnapshot.getChildren()) {

                    val v1 = dsp.child("name").getValue().toString()
                    val v23 = dsp.child("description").getValue().toString()
                    val v2 = dsp.child("option1").getValue().toString()
                    val v3 = dsp.child("option2").getValue().toString()
                    val v4 = dsp.child("option3").getValue().toString()
                    val v5 = dsp.child("option4").getValue().toString()
                    val v6 = dsp.child("option5").getValue().toString()
                    val v7 = dsp.child("option6").getValue().toString()
                    val v8 = dsp.child("option7").getValue().toString()
                    val v9 = dsp.child("option8").getValue().toString()
                    val v10 = dsp.child("option9").getValue().toString()
                    val v11 = dsp.child("option10").getValue().toString()
                    vote = VoteNames(v1, v23, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11)
                    votelists.add(vote)
                }
                Log.d("gghh", "success")
                showListUi(votelists)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("gghh", "failed")
            }
        })
    }


    private fun showListUi(votelists: java.util.ArrayList<VoteNames>) {
        val customListAdapter = CustomListAdapter(context as HomeActivity, votelists)

        lvVotings?.let {
            progressbar1234.visibility= View.INVISIBLE
            it.apply {
            adapter = customListAdapter
            customListAdapter.notifyDataSetChanged()
        }
        }
    }
}