package com.example.ratinadeticpro.Ui.ui.RsultFragment


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratinadeticpro.data.Firebase.PredictImg
import com.example.ratinadeticpro.data.db.PredictImgEntity
import com.example.ratinadeticpro.data.db.WhatToDoEntity
import com.example.ratinadeticpro.data.model.Rows
import com.example.ratinadeticpro.data.model.RowsWithDescrip
import com.example.ratinadeticpro.data.repo.Repo
import com.google.firebase.database.FirebaseDatabase
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
    private lateinit var result: RowsWithDescrip
    private lateinit var resultBeforeDisrip: Rows
    private lateinit var desc: WhatToDoEntity
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
