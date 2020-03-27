package com.example.ratinadeticpro.Ui.ui.Chart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ratinadeticpro.R

import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.dashboard_fragment.*


class ChartFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.dashboard_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noOfEmp = ArrayList<BarEntry>()

        noOfEmp.add(BarEntry(945f, 0))
        noOfEmp.add(BarEntry(1040f, 1))
        noOfEmp.add(BarEntry(1133f, 2))
        noOfEmp.add(BarEntry(1240f, 3))
        noOfEmp.add(BarEntry(1369f, 4))
        noOfEmp.add(BarEntry(1487f, 5))
        noOfEmp.add(BarEntry(1501f, 6))
        noOfEmp.add(BarEntry(1645f, 7))
        noOfEmp.add(BarEntry(1578f, 8))
        noOfEmp.add(BarEntry(1695f, 9))

        val year = ArrayList<String>()

        year.add("2008")
        year.add("2009")
        year.add("2010")
        year.add("2011")
        year.add("2012")
        year.add("2013")
        year.add("2014")
        year.add("2015")
        year.add("2016")
        year.add("2017")

        val barDatSet = BarDataSet(noOfEmp, "No Of Employee")
        barchart.animateY(5000)
        val data = BarData(year, barDatSet)
        barDatSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barchart.data = data
    }


}
