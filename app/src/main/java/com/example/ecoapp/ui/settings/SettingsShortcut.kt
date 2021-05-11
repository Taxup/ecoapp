package com.example.ecoapp.ui.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ecoapp.MainActivity
import com.example.ecoapp.R

class SettingsShortcut: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val relativeLayout = ConstraintLayout(this as Activity)
        val rlp = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)

        val settingsFragment = FrameLayout(this as Activity)
        val flp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)

        settingsFragment.layoutParams = flp
//        settingsFragment.tag =

        relativeLayout.addView(settingsFragment)
        settingsFragment.id = R.id.fragment

        fragmentTransaction.add(settingsFragment.id, SettingsFragment(), "temporarySettingsFragment")
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        setContentView(relativeLayout, rlp)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Only if on Lollipop or newer versions.
        finishAndRemoveTask()
        startActivity(Intent(this, MainActivity::class.java))
    }
}