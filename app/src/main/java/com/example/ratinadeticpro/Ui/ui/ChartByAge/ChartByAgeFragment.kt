package com.example.ratinadeticpro.Ui.ui.ChartByAge


import android.content.Context
import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration

import com.example.ratinadeticpro.R
import com.example.ratinadeticpro.Ui.ui.ViewModelFactory.ViewModelFactory
import com.example.ratinadeticpro.databinding.FragmentChartByAgeBinding
import com.example.ratinadeticpro.ui.ui.ChartByAge.SearchAdapter

import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_chart_by_age.img_noData
import kotlinx.android.synthetic.main.fragment_chart_by_age.prof
import javax.inject.Inject

class ChartByAgeFragment : Fragment() {
    lateinit var viewModel: ChartByAgeViewModel
    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var binding: FragmentChartByAgeBinding
    lateinit var adapter : SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_chart_by_age,
                container,
                false
            )
        with(binding.myRecyclerView) {
            setHasFixedSize(true)
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        }
        adapter =SearchAdapter()


        binding.myRecyclerView.adapter = adapter


        //Dividers
        val itemDecor = DividerItemDecoration(context, ClipDrawable.HORIZONTAL)
        binding.myRecyclerView.addItemDecoration(itemDecor)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProviders.of(this, factory).get(ChartByAgeViewModel::class.java).also {

                it.getPost()


            }

        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_chartByAgeFragment_to_dashboradResearcherFragment)
        }
        with(binding.myRecyclerView) {
            setHasFixedSize(true)
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        }
        viewModel.mutableList.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                img_noData.visibility = View.VISIBLE

            } else {
                adapter.submitList(it)
            }
            Log.v("ListSize", it.size.toString())
            prof.visibility = View.GONE
            Log.v("ListSize", it.size.toString())


        })

        viewModel.mutableError.observe(viewLifecycleOwner, Observer { errorLabel ->

            //database
            if (errorLabel.isNotEmpty()) {


                prof.visibility = View.GONE
                img_noData.visibility = View.VISIBLE
                Toast.makeText(context, "No Data Yet Start Now", Toast.LENGTH_LONG).show()

                Toast.makeText(context, "No Data Yet Start Now", Toast.LENGTH_LONG).show()

            }
        })


    }

//    private fun drawChart(it: List<CountOfType>) {
//        val noOfEmp = ArrayList<BarEntry>()
//        val countOfTypes = ArrayList<String>()
//
//        for ((count, item) in it.withIndex()) {
//            noOfEmp.add(BarEntry(item.count.toFloat(), count))
//            countOfTypes.add(item.type)
//
//        }
//
//        val barDatSet = BarDataSet(noOfEmp, "Ages")
//        barchart_age.animateY(3000)
//        val data = BarData(countOfTypes, barDatSet)
//        barDatSet.setColors(ColorTemplate.COLORFUL_COLORS)
//        barchart_age.data = data
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)


    }


}
