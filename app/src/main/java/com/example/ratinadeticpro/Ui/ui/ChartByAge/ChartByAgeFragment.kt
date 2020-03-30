package com.example.ratinadeticpro.Ui.ui.ChartByAge


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.example.ratinadeticpro.R
import com.example.ratinadeticpro.Ui.ui.ChartByGender.ChartByGenderViewModel
import com.example.ratinadeticpro.Ui.ui.ViewModelFactory.ViewModelFactory
import com.example.ratinadeticpro.data.model.CountOfType
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_chart_by_age.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ChartByAgeFragment : Fragment() {
    lateinit var viewModel: ChartByAgeViewModel
    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart_by_age, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProviders.of(this, factory).get(ChartByAgeViewModel::class.java).also {

                it.getPost()


            }

        floatingActionButtonAge.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_chartByAgeFragment_to_dashboradResearcherFragment)
        }

        viewModel.mutableList.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {

                drawChart(it)
            }
            Log.v("ListSize", it.size.toString())
        })

        viewModel.mutableError.observe(this, Observer { errorLabel ->

            //database
            if (errorLabel.isNotEmpty()) {


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

        val barDatSet = BarDataSet(noOfEmp, "Ages")
        barchart_age.animateY(3000)
        val data = BarData(countOfTypes, barDatSet)
        barDatSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barchart_age.data = data
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)


    }


}
