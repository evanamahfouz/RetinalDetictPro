package com.example.ratinadeticpro.ui.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.ratinadeticpro.R
import com.example.ratinadeticpro.databinding.ActivityLunchFragmentBinding

class LunchResearcherFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()

        DataBindingUtil.setContentView<ActivityLunchFragmentBinding>(
            this,
            R.layout.activity_lunch_researcher_fragment
        )


    }
}
