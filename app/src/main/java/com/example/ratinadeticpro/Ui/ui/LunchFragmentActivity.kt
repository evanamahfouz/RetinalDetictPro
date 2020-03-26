package com.example.ratinadeticpro.Ui.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.ratinadeticpro.R
import kotlinx.android.synthetic.main.activity_lunch_fragment.*

class LunchFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lunch_fragment)
        Navigation.findNavController(this, R.id.nav_host_frag).also {
            NavigationUI.setupWithNavController(bottomNavigationView, it)
        }

    }
}
