package com.example.ratinadeticpro.ui.ui.reportsWithSearch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratinadeticpro.data.model.PredictImg
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChartByAgeViewModel @Inject constructor(
    private val fireBaseDatabase: FirebaseDatabase
) : ViewModel() {
    val mutableList = MutableLiveData<List<PredictImg>>()
    val mutableError = MutableLiveData<String>()
    private val menu: MutableList<PredictImg> = mutableListOf()

    fun getPost() {
        viewModelScope.launch {
            try {

                withContext(Dispatchers.IO) {
                    fireBaseDatabase.getReference("predictImg").orderByChild("id_patient")
                        .addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {
                            }

                            override fun onDataChange(dataSnapshot: DataSnapshot) {


                                menu.clear()

                                dataSnapshot.children.mapNotNullTo(menu) {
                                    it.getValue<PredictImg>(
                                        PredictImg::class.java
                                    )
                                }
                                Log.v("ViewModelChartAll11", menu.size.toString())

                                mutableList.value = menu
                            }
                        })

                }


            } catch (e: Exception) {
                Log.v("insideSignUpViewMode", e.message!!)
                mutableError.value = e.message
            }
        }
    }

}
