package com.example.ratinadeticpro.Ui.ui.History


import android.content.Context
import android.content.SharedPreferences
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
import com.example.ratinadeticpro.databinding.FragmentHistoryBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_history.*
import javax.inject.Inject


@Suppress("DEPRECATION")
class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    lateinit var adapter: HistoryAdapter


    lateinit var viewModel: HistoryViewModel
    lateinit var id: String
    @Inject
    lateinit var factory: ViewModelFactory
    @Inject
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_history,
                container,
                false
            )


        adapter = HistoryAdapter()
        with(binding.myRecyclerView) {
            setHasFixedSize(true)
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        }
        id = sharedPreferences.getString(getString(R.string.id_user_key), "")!!

        viewModel =
            ViewModelProviders.of(this, factory).get(HistoryViewModel::class.java).also {

                it.getPost(
                    id
                )
            }

        binding.myRecyclerView.adapter = adapter


        //Dividers
        val itemDecor = DividerItemDecoration(context, ClipDrawable.HORIZONTAL)
        binding.myRecyclerView.addItemDecoration(itemDecor)


        viewModel.mutableList.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                img_noData.visibility = View.VISIBLE

            } else {
                adapter.submitList(it)
            }
            Log.v("ListSize", it.size.toString())
            prof.visibility = View.GONE
        })

        viewModel.mutableError.observe(this, Observer { errorLabel ->

            //database
            if (errorLabel.isNotEmpty()) {


                prof.visibility = View.GONE
                img_noData.visibility = View.VISIBLE
                Toast.makeText(context, "No Data Yet Start Now", Toast.LENGTH_LONG).show()

            }
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floatingActionButton.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_historyFragment_to_dashBoradFrag)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
}
