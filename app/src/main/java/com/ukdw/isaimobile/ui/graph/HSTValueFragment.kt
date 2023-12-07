package com.ukdw.isaimobile.ui.graph

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.ukdw.isaimobile.R
import com.ukdw.isaimobile.services.CarbonDataChartService
import com.ukdw.isaimobile.services.Impl.CarbonDataChartServiceImpl

class HSTValueFragment : Fragment() {
    lateinit var barChart: BarChart
    private var carbonDataChartService: CarbonDataChartService = object : CarbonDataChartServiceImpl(){}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_h_s_t_value, container, false)
        barChart = view.findViewById(R.id.idBarChartHST)
        barChart.clear()
        carbonDataChartService.refreshData()

        carbonDataChartService.setDataBarChart(12, "HST")
        barChart.data = carbonDataChartService.loadDataBarChart(barChart, 0.65f, 0.01f, 0.16F, "HST")
        barChart.description.isEnabled = false
        barChart.legend.isEnabled = false

        val xAxis: XAxis = barChart.getXAxis()
        xAxis.textSize = 12F
        xAxis.valueFormatter = object : ValueFormatter(){
            override fun getFormattedValue(value: Float): String {
                return carbonDataChartService.getXAxisLabel()[value.toInt()]
            }
        }
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(true)
        xAxis.labelCount = 12
        xAxis.setCenterAxisLabels(false)

        val yAxisRight: YAxis = barChart.axisRight
        yAxisRight.isEnabled = false
        barChart.invalidate()
        return view
    }
}