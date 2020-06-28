package com.mrspd.csevotingapp.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import com.mrspd.csevotingapp.R
import com.mrspd.csevotingapp.ResultScreenActivity
import com.mrspd.csevotingapp.VotingPortalActivity
import com.mrspd.csevotingapp.model.VoteNames
import com.mrspd.csevotingapp.model.VoteStats

class CustomListAdapterResultsStats(
    private val context: Activity,
    private val votelists: ArrayList<VoteStats>
) :
    BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.custom_listliveiw_results ,null, true)
        val tvname = rowView.findViewById(R.id.tvName) as TextView
        val count = rowView.findViewById(R.id.tvCount) as TextView
        val cardView = rowView.findViewById(R.id.cardView) as CardView

        tvname.text = votelists[position].name
        count.text = votelists[position].count.toString()
        d("gghh",votelists[position].name)
        d("gghh",votelists[position].count.toString())
        cardView.setOnClickListener {
//            val intent = Intent(context, ResultScreenActivity::class.java)
//            val b = Bundle()
//            b.putString("votename", votelists[position])
//            intent.putExtras(b)
//            startActivity(context, intent, b)
        }
        return rowView
    }


    override fun getItem(position: Int): Any {
        return position
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return votelists.size
    }
}