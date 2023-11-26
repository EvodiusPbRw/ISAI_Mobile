package com.ukdw.isaimobile.ui.about

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.ukdw.isaimobile.MainActivity
import com.ukdw.isaimobile.R

class AboutActivity : AppCompatActivity() {
    private lateinit var titleAppbar: TextView
    private lateinit var backButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        titleAppbar = findViewById(R.id.titleAppbar)
        titleAppbar.text = "Tentang Kami"

        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}