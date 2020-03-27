package com.example.ratinadeticpro.Ui.ui.Dashboard_Main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.example.ratinadeticpro.R
import kotlinx.android.synthetic.main.fragment_dash_borad_.*

/**
 * A simple [Fragment] subclass.
 */
class DashBoradFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dash_borad_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_predict.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_dashBoradFrag_to_predictFragment2)
        }
        btn_chart.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_dashBoradFrag_to_chartFragment)

        }
        btn_history.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_dashBoradFrag_to_historyFragment)

        }

    }


}
