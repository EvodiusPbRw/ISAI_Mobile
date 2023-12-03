package com.ukdw.isaimobile.ui.graph

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.CandleStickChart
import com.ukdw.isaimobile.R
import com.ukdw.isaimobile.services.CarbonDataChartService
import com.ukdw.isaimobile.services.Impl.CarbonDataChartServiceImpl

class CarbonEmissionFragment : Fragment() {
    private var carbonDataChartService: CarbonDataChartService =  object :
        CarbonDataChartServiceImpl(){}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_carbon_emission, container, false)
        val candleStickChart: CandleStickChart = view.findViewById(R.id.candleStick)
        candleStickChart.description.text = "ISAI"
        val entries = carbonDataChartService.setDataCarbon()
        val data = carbonDataChartService.loadDataCarbon(entries)

        candleStickChart.data = data
        candleStickChart.invalidate()
        return view
    }
}