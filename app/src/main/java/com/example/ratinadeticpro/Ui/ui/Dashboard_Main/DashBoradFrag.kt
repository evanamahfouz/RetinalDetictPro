package com.example.ratinadeticpro.Ui.ui.Dashboard_Main


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.example.ratinadeticpro.R
import com.example.ratinadeticpro.Ui.ui.Login.LoginActivity
import kotlinx.android.synthetic.main.fragment_dash_borad_.*


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
            Navigation.findNavController(view)
                .navigate(R.id.action_dashBoradFrag_to_historyFragment)

        }
        btn_profile.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_dashBoradFrag_to_profileFragment)

        }
        btn_aboutUs.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_dashBoradFrag_to_aboutUsFragment)
        }
        btn_logout.setOnClickListener {
            Intent(context, LoginActivity::class.java).also {
                startActivity(it)

            }
        }

    }
}