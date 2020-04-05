@file:Suppress("DEPRECATION")

package com.example.ratinadeticpro.ui.ui.reportsWithSearch


import android.content.Context
import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.ratinadeticpro.R
import com.example.ratinadeticpro.data.model.PredictImg
import com.example.ratinadeticpro.databinding.FragmentChartByAgeBinding
import com.example.ratinadeticpro.ui.ui.viewModelFactory.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_chart_by_age.*
import javax.inject.Inject

@Suppress("DEPRECATION")
class ChartByAgeFragment : Fragment() {
    lateinit var viewModel: ChartByAgeViewModel
    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var binding: FragmentChartByAgeBinding
    lateinit var adapter: SearchAdapter
    lateinit var list: List<PredictImg>

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
        adapter = SearchAdapter()
        viewModel.mutableList.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                img_noData.visibility = View.VISIBLE

            } else {
                list = it
                adapter.submitList(it)
            }
            Log.v("ListSize", it.size.toString())
            prof.visibility = View.GONE
            Log.v("ListSize", it.size.toString())



            binding.myRecyclerView.adapter = adapter


            //Dividers
            val itemDecor = DividerItemDecoration(context, ClipDrawable.HORIZONTAL)
            binding.myRecyclerView.addItemDecoration(itemDecor)

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

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isEmpty()) {
                    Log.v("newText", newText.isEmpty().toString())
                    Log.v("newList", list.size.toString())

                    adapter.submitList(list)

                } else {
                    adapter.filter.filter(newText)
                }
                return false


            }
        })
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)


    }
}
