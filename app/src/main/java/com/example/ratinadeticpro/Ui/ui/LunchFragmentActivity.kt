package com.example.ratinadeticpro.Ui.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ratinadeticpro.R

class LunchFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide() //<< this

        setContentView(R.layout.activity_lunch_fragment)


    }
}
