package com.mrspd.csevotingapp.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import com.mrspd.csevotingapp.R
import com.mrspd.csevotingapp.VotingPortalActivity
import com.mrspd.csevotingapp.model.VoteNames
import java.util.ArrayList

class CustomListAdapter(
    private val context: Activity,
    private val votelists: ArrayList<VoteNames>
) :
    BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.custom_listliveiw, null, true)
        val tvname = rowView.findViewById(R.id.tvVotename) as TextView
        val cardView = rowView.findViewById(R.id.cardView) as CardView

        tvname.text = votelists[position].name
        cardView.setOnClickListener {
            val intent = Intent(context, VotingPortalActivity::class.java)
            val b = Bundle()
            b.putParcelable("myvote", votelists[position])
            intent.putExtras(b)
            startActivity(context, intent, b)
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