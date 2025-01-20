package com.example.mcs_qualification.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.mcs_qualification.R
import com.example.mcs_qualification.fragment.CartFragment
import com.example.mcs_qualification.fragment.HistoryFragment
import com.example.mcs_qualification.fragment.ProductFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class MainActivity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var frameLayout: FrameLayout? = null
    var toolBar: Toolbar? = null;
//    var btnStoreLocation: Button? = null

    private fun initialize() {
        tabLayout = findViewById(R.id.tabLayout)
        frameLayout = findViewById(R.id.frameLayout)
        toolBar = findViewById(R.id.toolBar);
//        btnStoreLocation = findViewById(R.id.btnStoreLocation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        setFragment(ProductFragment())

        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> setFragment(ProductFragment())
                    1 -> setFragment(CartFragment())
                    2 -> setFragment(HistoryFragment())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

//        btnStoreLocation!!.setOnClickListener { x: View? ->
//            val intent = Intent(
//                this,
//                MapActivity::class.java
//            )
//            startActivity(intent)
//        }

        setSupportActionBar(toolBar)
    }

    fun setFragment(frag: Fragment?) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, frag!!).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.itemStoreLocation){
            val intent = Intent(
                this,
                MapActivity::class.java
            )
            startActivity(intent)
        }

        return true
    }
}