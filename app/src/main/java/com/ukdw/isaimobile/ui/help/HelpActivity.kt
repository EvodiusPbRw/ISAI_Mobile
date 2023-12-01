package com.ukdw.isaimobile.ui.help

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ukdw.isaimobile.R

class HelpActivity : AppCompatActivity() {
    private lateinit var titleAppbar: TextView
    private lateinit var backButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        titleAppbar = findViewById(R.id.titleAppbar)
        titleAppbar.text = "Bantuan"

        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}