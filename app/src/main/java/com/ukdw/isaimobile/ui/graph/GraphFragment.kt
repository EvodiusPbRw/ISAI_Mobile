package com.ukdw.isaimobile.ui.graph

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.google.android.material.tabs.TabLayout
import com.ukdw.isaimobile.MainActivity
import com.ukdw.isaimobile.R
import com.ukdw.isaimobile.adapter.ViewPagerAdapter


class GraphFragment : Fragment() {
    lateinit var activity: MainActivity
    private lateinit var pager: ViewPager
    private lateinit var tab: TabLayout
    private lateinit var bar: Toolbar

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_graph, container, false)
        pager = view.findViewById(R.id.pager)
        tab = view.findViewById(R.id.tab_layout)

        val adapter = ViewPagerAdapter(activity.supportFragmentManager)

        adapter.addFragment(CarbonAbsorbedFragment(), "Karbon Terserap")
        adapter.addFragment(CarbonEmissionFragment(), "Emisi Karbon")
        adapter.addFragment(HSTValueFragment(), "Agregat Karbon")

        pager.adapter = adapter
        tab.setupWithViewPager(pager)

        val bottomUp: Animation = AnimationUtils.loadAnimation(
            context,
            R.anim.animation_bottom_up
        )
        val bottomDown: Animation = AnimationUtils.loadAnimation(
            context,
            R.anim.animation_bottom_down
        )

        val spinnerTahun = view.findViewById<Spinner>(R.id.spinnerTahun)
        val spinnerKarbon = view.findViewById<Spinner>(R.id.spinnerDataKarbon)
        val spinnerKomoditas = view.findViewById<Spinner>(R.id.spinnerKomoditas)

        spinnerTahun.adapter = ArrayAdapter(activity,
            android.R.layout.simple_spinner_item, listOf("2023", "2022")
        )

        spinnerKarbon.adapter = ArrayAdapter(activity,
            android.R.layout.simple_spinner_item, listOf("Padi", "Cabai")
        )

        spinnerKomoditas.adapter = ArrayAdapter(activity,
            android.R.layout.simple_spinner_item, listOf("Klaten", "Yogyakarta")
        )

        val filterButton = view.findViewById<ImageButton>(R.id.filterButton)
        val closeFilterButton = view.findViewById<ViewGroup>(R.id.bgFilterButton)
        val hiddenPanel = view.findViewById(R.id.filterLayout) as ViewGroup

        filterButton.setOnClickListener {
            filterButton.setImageResource(R.drawable.filter_clicked_icon)
            hiddenPanel.startAnimation(bottomUp)
            hiddenPanel.visibility = View.VISIBLE
        }

        closeFilterButton.setOnClickListener {
            filterButton.setImageResource(R.drawable.filter_icon)
            hiddenPanel.startAnimation(bottomDown)
            hiddenPanel.visibility = View.GONE
        }

        return view
    }
}