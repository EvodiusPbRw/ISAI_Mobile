package com.ukdw.isaimobile.ui.menu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ukdw.isaimobile.MainActivity
import com.ukdw.isaimobile.R
import com.ukdw.isaimobile.ui.about.AboutActivity


class MenuActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var backButton: ImageButton
    private lateinit var aboutButton: FrameLayout
    private lateinit var calculatorButton: FrameLayout
    private lateinit var graphButton: FrameLayout
    private lateinit var titleAppbar: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        titleAppbar = findViewById(R.id.titleAppbar)
        titleAppbar.text = "Menu"

        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener { e -> val i = Intent(this, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("selected_item", R.id.home)
            i.putExtra("fragment_name", "home")
            setResult(Activity.RESULT_OK, i)
            finish()
        }

        aboutButton = findViewById(R.id.frameLayoutAbout)
        aboutButton.setOnClickListener { e ->
            val i = Intent(this, AboutActivity::class.java)
            startActivity(i)
        }

        calculatorButton = findViewById(R.id.frameLayoutCalculator)
        calculatorButton.setOnClickListener { e ->
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("selected_item", R.id.calculator)
            i.putExtra("fragment_name", "calculator")
            setResult(Activity.RESULT_OK, i)
            finish()
        }

        graphButton = findViewById(R.id.frameLayoutTrend)
        graphButton.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("selected_item", R.id.graph)
            i.putExtra("fragment_name", "graph")
            setResult(Activity.RESULT_OK, i)
            finish()
        }
    }
}