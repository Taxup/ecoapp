package com.example.ecoapp.ui.main

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.bikomobile.donutprogress.DonutProgress
import com.example.ecoapp.R
import java.util.*
import kotlin.math.abs

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        // TODO: Setup things for lastDay of path.
        val donutProgress: DonutProgress = (context as Activity).findViewById(R.id.progressBar)
        val dayOfPath: Int = abs(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - PreferenceManager.getDefaultSharedPreferences(context).getInt("startDay", 1))
        ((context as Activity).findViewById<TextView>(R.id.advice_title)).text = resources.getStringArray(R.array.advicesTitle)[dayOfPath]
        ((context as Activity).findViewById<TextView>(R.id.advice_subtitle)).text = resources.getStringArray(R.array.advicesSubtitle)[dayOfPath]
        donutProgress.text = dayOfPath.toString()
        donutProgress.max = 28 * 10
        val progressText: String = getString(R.string.main_day) + " " + (dayOfPath + 1) + " " + getString(R.string.main_of) + " 28"
        ((context as Activity).findViewById<TextView>(R.id.progressText)).text = progressText

        object : Thread() {
            override fun run() {
                donutProgress.setProgressWithAnimation((dayOfPath * 10), 10)
            }
        }.start()
        Handler().postDelayed({
            // Here you can do something on the exact moment the animation finish.
            // TODO: Do something.
        }, (dayOfPath * 10 * 10).toLong())

    }
}
