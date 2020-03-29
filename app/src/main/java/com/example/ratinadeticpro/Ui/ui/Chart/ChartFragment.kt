package com.example.ratinadeticpro.Ui.ui.Chart

import android.content.Context
import android.content.SharedPreferences
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
import com.example.ratinadeticpro.Ui.ui.ViewModelFactory.ViewModelFactory
import com.example.ratinadeticpro.data.model.CountOfType

import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.chart_fragment.*
import javax.inject.Inject


@Suppress("DEPRECATION")
class ChartFragment : Fragment() {
    lateinit var viewModel: ChartViewModel
    lateinit var id: String
    @Inject
    lateinit var factory: ViewModelFactory
    @Inject
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.chart_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = sharedPreferences.getString(getString(R.string.id_user_key), "")!!

        viewModel =
            ViewModelProviders.of(this, factory).get(ChartViewModel::class.java).also {

                it.getPost(
                    id
                )
            }

        floatingActionButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_dashboardFragment2_to_dashBoradFrag)
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

        val barDatSet = BarDataSet(noOfEmp, "Num Of Inserted Image With The Same Type")
        barchart.animateY(5000)
        val data = BarData(countOfTypes, barDatSet)
        barDatSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barchart.data = data
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
}
