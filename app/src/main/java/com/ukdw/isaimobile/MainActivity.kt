package com.ukdw.isaimobile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapView
import com.ukdw.isaimobile.ui.calculator.CalculatorFragment
import com.ukdw.isaimobile.ui.graph.GraphFragment
import com.ukdw.isaimobile.ui.home.HomeFragment
import com.ukdw.isaimobile.ui.menu.MenuFragment

class MainActivity : AppCompatActivity() {

//    private lateinit var appBarConfiguration: AppBarConfiguration
//    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(applicationContext, R.string.access_token.toString())

        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment())
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    bottomNav.visibility = View.VISIBLE
                    loadFragment(HomeFragment())
                    true
                }
                R.id.graph -> {
                    loadFragment(GraphFragment())
                    true
                }
                R.id.calculator -> {
                    loadFragment(CalculatorFragment())
                    true
                }
                R.id.menu -> {
                    bottomNav.visibility = View.GONE
                    loadFragment(MenuFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }

    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}