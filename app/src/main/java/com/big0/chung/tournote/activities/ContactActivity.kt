package com.big0.chung.tournote.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.big0.chung.tournote.R
import com.big0.chung.tournote.fragments.AboutHelpFragment
import com.big0.chung.tournote.utilities.inTransaction

class ContactActivity : AppCompatActivity() {

    companion object {
        val KEY_SHOW_WHAT = "show_what"
        val VALUE_SHOW_ABOUT = "show_about"
        val VALUE_SHOW_HELP = "show_help"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val bundle = intent.extras
        if (bundle != null) {
            val message = bundle.getString(KEY_SHOW_WHAT)
            val url = if (message == VALUE_SHOW_ABOUT) "http://rezero.wikia.com/wiki/Rem"
            else "http://rezero.wikia.com/wiki/Ram"
            val aboutHelpFragment = AboutHelpFragment.newInstance(url)

            supportFragmentManager.inTransaction { add(R.id.contactMainFrame, aboutHelpFragment) }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
