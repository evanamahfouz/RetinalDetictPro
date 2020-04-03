package com.example.ratinadeticpro.Ui.ui.ChartByAge

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratinadeticpro.data.model.PredictImg
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

class ChartByAgeViewModel @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val repo: Repo
) : ViewModel() {
    val mutableList = MutableLiveData<List<PredictImg>>()
    val mutableError = MutableLiveData<String>()
    private val menu: MutableList<PredictImg> = mutableListOf()

    fun getPost() {
        viewModelScope.launch {
            try {

                withContext(Dispatchers.IO) {
                    firebaseDatabase.getReference("predictImg").orderByChild("id_patient")
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
