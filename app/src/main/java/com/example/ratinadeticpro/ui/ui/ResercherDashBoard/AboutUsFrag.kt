package com.example.ratinadeticpro.ui.ui.ResercherDashBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.ratinadeticpro.R
import kotlinx.android.synthetic.main.fragment_about_us_researcher.*

class AboutUsFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us_researcher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_goDash.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_aboutUsFrag_to_dashboradResearcherFragment)
        }
    }
}