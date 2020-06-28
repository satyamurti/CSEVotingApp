package com.mrspd.csevotingapp.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mrspd.csevotingapp.R
import com.mrspd.csevotingapp.adapter.CustomListAdapter
import com.mrspd.csevotingapp.adapter.CustomListAdapterResults
import com.mrspd.csevotingapp.model.VoteNames
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_results.*

class ResultFragment : Fragment(R.layout.fragment_results){

    lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference
    var votelists = ArrayList<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadResults()
    }

    private fun loadResults() {
        progressbar125675.visibility= View.VISIBLE
        mDatabase = FirebaseDatabase.getInstance().getReference("Results")
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                votelists.clear()
                for (dsp in dataSnapshot.getChildren()) {

                    val v1 = dsp.key.toString()
                    votelists.add(v1)
                }
                Log.d("gghh", "success")
                showListUi(votelists)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("gghh", "failed")
            }
        })
    }

    private fun showListUi(votelists: java.util.ArrayList<String>) {
        val customListAdapter = CustomListAdapterResults(context as Activity, votelists)


        lvResults.apply {
            progressbar125675.visibility= View.INVISIBLE
            adapter = customListAdapter
            customListAdapter.notifyDataSetChanged()
        }
    }
}