package com.ukdw.isaimobile.ui.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import com.ukdw.isaimobile.R

class CalculatorFragment : Fragment() {
    private lateinit var calculateButton: FrameLayout
    private lateinit var resultCarbon: TextView
    private lateinit var totalDistanceInput: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_calculator, container, false)
        resultCarbon = view.findViewById(R.id.resultCarbon)
        resultCarbon.text = ""

        calculateButton = view.findViewById(R.id.btnCalculate)

        totalDistanceInput = view.findViewById(R.id.totalDistanceInput)
        calculateButton.setOnClickListener {
            var totalDistance: Double = totalDistanceInput.text.toString().toDouble()
            var factorEmmision: Double = 2.4
            var formula: Double = totalDistance * factorEmmision
            resultCarbon.text = formula.toString() + " Kg/KM"
        }

        return view
    }
}