package com.example.ratinadeticpro.Ui.ui.RsultFragment


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratinadeticpro.data.model.PredictImg
import com.example.ratinadeticpro.data.Firebase.User
import com.example.ratinadeticpro.data.db.PredictImgEntity
import com.example.ratinadeticpro.data.db.WhatToDoEntity
import com.example.ratinadeticpro.data.model.Rows
import com.example.ratinadeticpro.data.model.RowsWithDescrip
import com.example.ratinadeticpro.data.repo.Repo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ResultViewModel @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val repo: Repo
) : ViewModel() {

    val mutableList = MutableLiveData<RowsWithDescrip>()
    val mutableError = MutableLiveData<String>()
    private lateinit var resultBeforeDisrip: Rows
    private lateinit var desc: WhatToDoEntity
    private lateinit var gender: String

    fun getPost(id: String, eye: String) {
        viewModelScope.launch {
            try {
                val date = getCurrentDateTime()
                val dateInString = date.toString("yyyy/MM/dd")

                withContext(Dispatchers.IO) {
                    resultBeforeDisrip = repo.findResultFromGoogleSheet().rows.last {
                        it.iduser == Integer.parseInt(id)
                    }


                    desc = repo.getPredictResult(
                        PredictImgEntity(
                            ID_patient = id, eye_part = eye, prediction = resultBeforeDisrip.type,
                            Probability = resultBeforeDisrip.probability.toString(),
                            date = dateInString
                        )
                    )
                }


                firebaseDatabase.getReference("predictImg").also {
                    it.child(it.push().key!!).setValue(
                        PredictImg(
                            id, eye, resultBeforeDisrip.type,
                            resultBeforeDisrip.probability.toString(),
                            dateInString
                        )
                    )
                }
                mutableList.value =
                    resultBeforeDisrip.mapWithDiscrip(desc.Result)
//update counter
                firebaseDatabase.getReference("users").orderByChild("id_User").equalTo(id).also {
                    it.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                        }

                        override fun onDataChange(dataSnapshot1: DataSnapshot) {
                            gender =
                                dataSnapshot1.children.first().getValue(User::class.java)!!.gender
                            Log.v("ResultviewMODEL", gender)

                            // listen for single change
                            firebaseDatabase.getReference(resultBeforeDisrip.type).also { value ->
                                value.orderByChild(gender)
                                    .addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onCancelled(p0: DatabaseError) {
                                            Log.v("ViewModelREesult", p0.message)
                                        }

                                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                                            val count =
                                                dataSnapshot.child(gender).child("count")
                                                    .value.toString()
                                            Log.v(
                                                "Before", resultBeforeDisrip.type + " " + count
                                            )

                                            value.child(gender).child("count")
                                                .setValue(((Integer.parseInt(count) + 1)).toString())
                                            Log.v("After", ((count + 1)))

                                        }

                                    })

                            }
                            firebaseDatabase.getReference("CountEach")
                                .child(resultBeforeDisrip.type).also { value ->
                                value.addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onCancelled(p0: DatabaseError) {
                                        Log.v("ViewModelREesult", p0.message)
                                    }

                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                        val count =
                                            dataSnapshot.child("count")
                                                .value.toString()
                                        Log.v(
                                            "Before111", resultBeforeDisrip.type + " " + count
                                        )

                                        value.child("count")
                                            .setValue(((Integer.parseInt(count) + 1)).toString())
                                        Log.v("After111", ((count + 1)))

                                    }

                                })
                            }
                        }
                    })
                }
            } catch (e: Exception) {
                mutableError.value = e.message
            }
        }
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }


}
