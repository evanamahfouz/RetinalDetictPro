package com.example.ratinadeticpro.Ui.ui.ChartOverAllFragment


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.example.ratinadeticpro.R
import com.example.ratinadeticpro.Ui.ui.ViewModelFactory.ViewModelFactory
import com.example.ratinadeticpro.data.model.CountOfType
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_chart_over_all.*
import kotlinx.android.synthetic.main.fragment_chart_over_all.floatingActionButton
import kotlinx.android.synthetic.main.fragment_chart_over_all.prof
import kotlinx.android.synthetic.main.fragment_history.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
@Suppress("DEPRECATION")
class ChartOverAllFragment : Fragment() {
    lateinit var viewModel: ChartOverAllViewModel
    @Inject
    lateinit var factory: ViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart_over_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProviders.of(this, factory).get(ChartOverAllViewModel::class.java).also {

                it.getPost()


            }

        floatingActionButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_chartOverAllFragment_to_dashboradResearcherFragment)
        }

        viewModel.mutableList.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {

                drawChart(it)
            }
            Log.v("ListSize", it.size.toString())
        })

        viewModel.mutableError.observe(viewLifecycleOwner, Observer { errorLabel ->

            //database
            if (errorLabel.isNotEmpty()) {

                prof.visibility = View.GONE

                Toast.makeText(context, "No Data Yet Start Now", Toast.LENGTH_LONG).show()

            }
        })


    }

    private fun drawChart(it: List<CountOfType>) {
        val noOfEmp = ArrayList<BarEntry>()
        val countOfTypes = ArrayList<String>()

        for ((count, item) in it.withIndex()) {
            noOfEmp.add(BarEntry(item.count.toFloat(), count))
            countOfTypes.add(item.type)

        }

        val barDatSet = BarDataSet(noOfEmp, "Num Of Inserted Image Within Type")
        barchart.animateY(3000)
        val data = BarData(countOfTypes, barDatSet)
        barchart.xAxis.setLabelsToSkip(0)
        Log.v("FragmentChartOver", data.xVals.toString() + " " + data.xValCount)
        barDatSet.setColors(ColorTemplate.COLORFUL_COLORS)
        prof.visibility = View.GONE
        barchart.isVisible = true
        barchart.data = data
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

}
