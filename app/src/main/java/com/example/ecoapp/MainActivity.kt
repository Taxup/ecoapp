package com.example.ecoapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.ecoapp.ui.dashboard.DashboardFragment
import com.example.ecoapp.notifications.NotificationReceiver
import com.example.ecoapp.ui.main.MainFragment
import com.example.ecoapp.ui.settings.SettingsFragment
import com.example.ecoapp.ui.news.article_list.NewsFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DashboardFragment.OnFragmentInteractionListener {
    private val fragmentManager = supportFragmentManager

    private val mainFragment = MainFragment()
    private val newsFragment = NewsFragment()
    private val dashboardFragment = DashboardFragment()
    private val settingsFragment = SettingsFragment()
    private var selectedFragment: Fragment = mainFragment

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                fragmentManager.beginTransaction()
                        .hide(selectedFragment)
                        .show(mainFragment)
                        .commit()
                selectedFragment = mainFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_news -> {
                fragmentManager.beginTransaction()
                        .hide(selectedFragment)
                        .show(newsFragment)
                        .commit()
                selectedFragment = newsFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                fragmentManager.beginTransaction()
                        .hide(selectedFragment)
                        .show(dashboardFragment)
                        .commit()
                selectedFragment = dashboardFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                fragmentManager.beginTransaction()
                        .hide(selectedFragment)
                        .show(settingsFragment)
                        .commit()
                selectedFragment = settingsFragment
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString())

        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("firstStart", true)) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = preferences.edit()
            editor.putInt("startDay", Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
            editor.putInt("startMonth", Calendar.getInstance().get(Calendar.MONTH))
            editor.putBoolean("firstStart", false)
            editor.apply()

            val pendingIntent = PendingIntent.getBroadcast(
                    this,
                    1201,
                    Intent(
                            this,
                            NotificationReceiver::class.java
                    ),
                    PendingIntent.FLAG_UPDATE_CURRENT
            )
            val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()

            when (PreferenceManager.getDefaultSharedPreferences(this).getInt("notificationsTime", 0)) {
                0 -> calendar.set(Calendar.HOUR_OF_DAY, 9)
                1 -> calendar.set(Calendar.HOUR_OF_DAY, 12)
                2 -> calendar.set(Calendar.HOUR_OF_DAY, 18)
            }

            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)

            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    (1000 * 60 * 60 * 24).toLong(),
                    pendingIntent
            )
        }

        // Load main fragment at start.
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.fragment, mainFragment)
        fragmentTransaction.add(R.id.fragment, newsFragment)
        fragmentTransaction.add(R.id.fragment, dashboardFragment)
        fragmentTransaction.add(R.id.fragment, settingsFragment)

        fragmentTransaction.hide(newsFragment)
        fragmentTransaction.hide(dashboardFragment)
        fragmentTransaction.hide(settingsFragment)

        fragmentTransaction.show(mainFragment)

        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        findViewById<BottomNavigationView>(R.id.navigation).setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onFragmentInteraction(uri: Uri) {
        // TODO: To be understood.
    }

    // This handles the back button, to prevent the user from seeing the blank fragment.
    private var doubleBackToExitPressedOnce: Boolean = true
    override fun onBackPressed() {
        if (!doubleBackToExitPressedOnce) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                // Only if on Lollipop or newer versions.
                finishAndRemoveTask()
            } else {
                // Only if on a version older than Lollipop.
                finishAffinity()
            }
        }

        this.doubleBackToExitPressedOnce = false
        Toast.makeText(this, getString(R.string.press_exit), Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ doubleBackToExitPressedOnce = true }, 1000)
    }
}
