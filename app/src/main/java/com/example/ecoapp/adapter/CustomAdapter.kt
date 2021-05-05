package com.example.ecoapp.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ecoapp.R
import com.example.ecoapp.ui.dashboard.advice.AdviceView
import com.example.ecoapp.model.Advice
import java.util.*

class CustomAdapter internal constructor(private val context: Context, private val adviceList: ArrayList<Advice>) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return adviceList.size
    }

    override fun getItem(i: Int): Any {
        return adviceList[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        // TODO: Use recyclerView.
        val holder: ViewHolder
        var retView: View? = view

        // Retrieve the ViewHolder.
        if (retView == null) {
            retView = inflater.inflate(R.layout.custom_adapter, null) as ConstraintLayout
            holder = ViewHolder(retView)
            retView.tag = holder
        } else {
            retView = view
            holder = retView!!.tag as ViewHolder
        }

        val advice: Advice = adviceList[i]

        // Set text in the TextViews.
        holder.dashboardAdvice.text = advice.title
        holder.dashboardDate.text = advice.date.toString()
        holder.dashboardMonth.text = (context).resources.getStringArray(R.array.months)[advice.month!!]

        // Set if the text has to be strike-through.
        if (advice.bar!!) holder.dashboardAdvice.paintFlags = holder.dashboardAdvice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        else holder.dashboardAdvice.paintFlags = holder.dashboardAdvice.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

        // Set a listener for click events.
        retView.setOnClickListener {
            val intent = Intent(context, AdviceView::class.java)
            intent.putExtra("advice", advice.title)
            intent.putExtra("dayOfPath", advice.dayOfPath)
            intent.putExtra("date", advice.date)
            intent.putExtra("month", (context).resources.getStringArray(R.array.months)[advice.month!!])
            context.startActivity(intent)
        }

        // Log advices for debugging scopes.
        Log.d((context as Activity).localClassName, advice.title + " $i ++++++++")

        return retView
    }

    class ViewHolder(view: View?) {
        val dashboardAdvice: TextView = view?.findViewById(R.id.dashboard_advice) as TextView
        val dashboardDate: TextView = view?.findViewById(R.id.dashboard_date) as TextView
        val dashboardMonth: TextView = view?.findViewById(R.id.dashboard_month) as TextView
    }
}