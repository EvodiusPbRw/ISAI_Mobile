package com.ukdw.isaimobile.ui.graph

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private lateinit var pager: ViewPager // creating object of ViewPager
    private lateinit var tab: TabLayout  // creating object of TabLayout
    private lateinit var bar: Toolbar    // creating object of ToolBar

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

        pager.adapter = adapter
        tab.setupWithViewPager(pager)

        return view
    }
}