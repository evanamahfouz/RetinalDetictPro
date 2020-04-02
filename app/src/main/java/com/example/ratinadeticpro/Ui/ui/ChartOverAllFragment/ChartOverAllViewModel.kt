package com.example.ratinadeticpro.Ui.ui.ChartOverAllFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratinadeticpro.data.Firebase.CountEach
import com.example.ratinadeticpro.data.Firebase.PredictImg
import com.example.ratinadeticpro.data.Firebase.User
import com.example.ratinadeticpro.data.Firebase.WhatToDo
import com.example.ratinadeticpro.data.model.CountOfType
import com.example.ratinadeticpro.data.repo.Repo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class ChartOverAllViewModel @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val repo: Repo
) : ViewModel() {
    val mutableList = MutableLiveData<List<CountOfType>>()
    val mutableError = MutableLiveData<String>()

    fun getPost() {
        viewModelScope.launch {
            try {
                firebaseDatabase.getReference("CountEach")
                    .addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                            Log.v("ViewModeChartAllError", p0.message)

                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            for (data in p0.children) {
                                data.getValue(CountEach::class.java)?.also {
                                    Log.v(
                                        "ViewModeChartOverAll",
                                        "CNV " + it.CNV + "NORMAL " + it.NORMAL
                                    )
                                    mutableList.value = listOf(
                                        CountOfType("CNV", Integer.parseInt(it.CNV)),
                                        CountOfType("NORMAL", Integer.parseInt(it.NORMAL)),
                                        CountOfType("DRUSEN", Integer.parseInt(it.DRUSEN)),
                                        CountOfType("DME", Integer.parseInt(it.DME))
                                    )


                                }
                            }
                        }
                    })
            } catch (e: Exception) {
                Log.v("insideSignUpViewMode", e.message!!)
                mutableError.value = e.message
            }
        }
    }

}
