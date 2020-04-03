package com.example.ratinadeticpro.Ui.ui.ChartByGender

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class ChartByGenderViewModel @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val repo: Repo
) : ViewModel() {
    val mutableList = MutableLiveData<List<CountOfType>>()
    val mutableError = MutableLiveData<String>()
    private val menu: MutableList<CountOfType> = mutableListOf()

    fun getPost(type: String) {
        viewModelScope.launch {
            try {
                firebaseDatabase.getReference(type)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                            Log.v("ViewModeChartAllError", p0.message)

                        }

                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            menu.clear()

                            dataSnapshot.children.mapNotNullTo(menu) {
                                it.getValue<CountOfType>(
                                    CountOfType::class.java
                                )
                            }
                            Log.v("ViewModelChartAll11", menu.size.toString())

                            mutableList.value = menu
                        }

                    })
            } catch (e: Exception) {
                Log.v("insideSignUpViewMode", e.message!!)
                mutableError.value = e.message
            }


        }
    }
}