package com.example.ratinadeticpro.Ui.ui.ChartByGender


import android.content.Context
import android.opengl.Visibility
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
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_chart_by_gender.*
import javax.inject.Inject
import com.github.mikephil.charting.data.*
import kotlinx.android.synthetic.main.fragment_chart_by_gender.floatingActionButton
import kotlinx.android.synthetic.main.predict_fragment.*
import java.lang.Boolean.TRUE


/**
 * A simple [Fragment] subclass.
 */
@Suppress("DEPRECATION")
class ChartByGenderFragment : Fragment() {
    lateinit var viewModel: ChartByGenderViewModel
    lateinit var type: String

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart_by_gender, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        radio_compare.setOnCheckedChangeListener { _, checkedId ->
            //            when (checkedId) {
//                radio_DME.id -> type = "DME"
//                radio_CNV.id -> type = "CNV"
//                radio_NORMAL.id -> type = "NORMAL"
//                radio_DUREON.id -> type = "DRUSEN"
//
//            }
            type = if (checkedId == radio_DME.id) {
                "DME"
            } else if (checkedId == radio_CNV.id) {
                "CNV"
            } else if (checkedId == radio_NORMAL.id) {
                "NORMAL"
            } else {
                "DRUSEN"
            }

            startPost(type)
        }



        floatingActionButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_chartByGenderFragment_to_dashboradResearcherFragment)
        }


    }

    private fun startPost(type: String="DME") {

        viewModel =
            ViewModelProviders.of(this, factory).get(ChartByGenderViewModel::class.java).also {

                it.getPost(type)

            }
        viewModel.mutableList.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {

                drawChart(it)
            }else{
                barchart_gender.clear()
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
        Log.v("InsideDraw", "YESSSSSSSS2")
        val noOfEmp = ArrayList<BarEntry>()
        val countOfTypes = ArrayList<String>()

        for ((count, item) in it.withIndex()) {
            noOfEmp.add(BarEntry(item.count.toFloat(), count))
            countOfTypes.add(item.type)

        }

        val barDatSet = BarDataSet(noOfEmp, "Man Vs Women")
        barchart_gender.animateY(3000)
        val data = BarData(countOfTypes, barDatSet)
        barDatSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barchart_gender.data = data

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

}
