package com.ukdw.isaimobile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mapbox.mapboxsdk.Mapbox
import com.ukdw.isaimobile.databinding.ActivityMainBinding
import com.ukdw.isaimobile.ui.calculator.CalculatorFragment
import com.ukdw.isaimobile.ui.graph.GraphFragment
import com.ukdw.isaimobile.ui.home.HomeFragment
import com.ukdw.isaimobile.ui.menu.MenuActivity

class MainActivity : AppCompatActivity() {
    companion object{
        const val MENU_REQUEST_CODE = 46172
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView
    private val defaultFragment: String = "home"
    private val defaultSelectedItem: Int = R.id.home

    private var changedFragment: String? = null
    private var changedSelectedItem: Int = defaultSelectedItem

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data

            changedFragment = data!!.getStringExtra("fragment_name")
            changedSelectedItem = data.getIntExtra("selected_item", defaultSelectedItem)

            changeFragment(changedFragment ?: defaultFragment)
            bottomNav.selectedItemId = changedSelectedItem
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Mapbox.getInstance(applicationContext, R.string.access_token.toString())

        setContentView(R.layout.activity_main)

//        val intent: Intent = intent;
//        changedFragment = if(intent.getStringExtra("fragment_name").isNullOrEmpty()) null else intent.getStringExtra("fragment_name").toString()
//        Log.d("Test", intent.getStringExtra("fragment_name").toString())
//        changeFragment(changedFragment ?: defaultFragment)

//
//        changedSelectedItem = intent.getIntExtra("selected_item", defaultSelectedItem)
//        bottomNav.selectedItemId = changedSelectedItem

        changeFragment(defaultFragment)

        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.selectedItemId = defaultSelectedItem
        bottomNav.setOnItemSelectedListener {
            Log.d("Test", it.itemId.toString())
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
                    var intent = Intent(this,MenuActivity::class.java)
                    startForResult.launch(intent)
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

    fun changeFragment(fragmentName: String){
        when(fragmentName){
            "home" -> loadFragment(HomeFragment())
            "calculator" -> loadFragment(CalculatorFragment())
            "graph" -> loadFragment(GraphFragment())
        }
    }
}