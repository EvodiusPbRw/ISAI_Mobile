package com.ukdw.isaimobile.ui.graph

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.ValueFormatter
import com.ukdw.isaimobile.R
import com.ukdw.isaimobile.services.CarbonDataChartService
import com.ukdw.isaimobile.services.Impl.CarbonDataChartServiceImpl


class CarbonAbsorbedFragment : Fragment() {
    private var carbonDataChartService: CarbonDataChartService =  object :
        CarbonDataChartServiceImpl(){}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_carbon_absorbed, container, false)
        val candleStickChart: CandleStickChart = view.findViewById(R.id.candleStick)
        candleStickChart.description.text = ""
        val entries = carbonDataChartService.setDataCarbon()
        val data = carbonDataChartService.loadDataCarbon(entries)

        candleStickChart.data = data

        val xAxis: XAxis = candleStickChart.getXAxis()
        xAxis.textSize = 12F
        xAxis.valueFormatter = object : ValueFormatter(){
            override fun getFormattedValue(value: Float): String {
                return carbonDataChartService.getXAxisLabel()[value.toInt()]
            }
        }
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.labelCount = 12

        candleStickChart.invalidate()
        return view
    }

}