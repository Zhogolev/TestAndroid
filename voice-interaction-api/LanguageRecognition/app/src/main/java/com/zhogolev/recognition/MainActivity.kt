package com.zhogolev.recognition

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.zhogolev.recognition.fragment.PageHistory
import com.zhogolev.recognition.fragment.Recognizer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        return when (item.itemId) {
            R.id.nav_recognizer -> {
                fragmentManager
                    .beginTransaction()
                    .replace(R.id.container, Recognizer())
                    .commit()

                drawer_layout.closeDrawer(GravityCompat.START)
                true
            }
            R.id.nav_history -> {
                fragmentManager
                    .beginTransaction()
                    .replace(R.id.container, PageHistory())
                    .commit()

                drawer_layout.closeDrawer(GravityCompat.START)
                true
            }

            else -> {
                false
            }

        }

    }
}
