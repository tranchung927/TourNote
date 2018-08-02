package com.big0.chung.tournote.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.big0.chung.tournote.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import com.big0.chung.tournote.utilities.translation
import kotlinx.android.synthetic.main.fab_layout.*
import com.big0.chung.tournote.utilities.TranslationType
import com.big0.chung.tournote.utilities.rota

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var isOnClickMenuButton: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            onMenuClick(view)
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.search -> {
                Toast.makeText(this, "Search button selected", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.help -> {
                val intent = Intent(this, ContactActivity::class.java)
                val bundle = Bundle()
                bundle.putString(ContactActivity.KEY_SHOW_WHAT, ContactActivity.VALUE_SHOW_HELP)
                intent.putExtras(bundle)
                startActivity(intent)
                return true
            }
            R.id.about -> {
                val intent = Intent(this, ContactActivity::class.java)
                val bundle = Bundle()
                bundle.putString(ContactActivity.KEY_SHOW_WHAT, ContactActivity.VALUE_SHOW_ABOUT)
                intent.putExtras(bundle)
                startActivity(intent)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun onMenuClick(view: View) {
        isOnClickMenuButton = !isOnClickMenuButton
        fab.isEnabled = false
        val from: Float = if (isOnClickMenuButton) 0f else 90f
        val to: Float = if (isOnClickMenuButton) 90f else 0f
        fab.rota(from, to)
        move(!isOnClickMenuButton)
    }

    fun move(isBack: Boolean) {
        val width = fab_one.width.toFloat() + 10
        val start: Float = if (isBack) -width else -(fab.width.toFloat() + 10 - width)
        val end: Float = if (isBack) 0f else -(fab.width.toFloat()) - 10
        if (isBack) close(start, width, end) else open(start, width, end)
    }

    fun open(start: Float, width: Float, end: Float) {
        fab_container.visibility = View.VISIBLE
        fab_container.setBackgroundColor(Color.parseColor("#80000000"))
        fab_four.translation(TranslationType.Y, start, end) {
            fab_three.translation(TranslationType.Y, start - width, end - width) {
                fab_two.translation(TranslationType.Y, start - 2 * width, end - 2 * width) {
                    fab_one.translation(TranslationType.Y, start - 3 * width, end - 3 * width) {
                        fab.isEnabled = true
                    }
                }
            }
        }

    }

    fun close(start: Float, width: Float, end: Float) {
        fab_container.setBackgroundColor(Color.parseColor("#00ffffff"))
        fab_one.translation(TranslationType.Y, start - 3 * width, end - 3 * width) {
            fab_one.visibility = View.INVISIBLE
            fab_two.translation(TranslationType.Y, start - 2 * width, end - 2 * width) {
                fab_two.visibility = View.INVISIBLE
                fab_three.translation(TranslationType.Y, start - width, end - width) {
                    fab_three.visibility = View.INVISIBLE
                    fab_four.translation(TranslationType.Y, start, end) {
                        fab_four.visibility = View.INVISIBLE
                        fab.isEnabled = true
                        fab_container.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }
}
