package com.example.ratinadeticpro.Ui.ui.ResercherDashBoard


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.example.ratinadeticpro.R
import kotlinx.android.synthetic.main.fragment_dashborad_researcher.*

class DashboradResearcherFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashborad_researcher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chart_Over_All.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_dashboradResearcherFragment_to_chartOverAllFragment)
        }
        chart_Gender.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_dashboradResearcherFragment_to_chartByGenderFragment)
        }
        chart_age.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_dashboradResearcherFragment_to_chartByAgeFragment)
        }
        floatingActionButtonDash.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_dashboradResearcherFragment_to_loginActivity)
        }
    }
}