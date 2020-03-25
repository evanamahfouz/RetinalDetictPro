package com.example.ratinadeticpro.Ui.ui.RsultFragment


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratinadeticpro.data.db.PredictImgEntity
import com.example.ratinadeticpro.data.model.Rows
import com.example.ratinadeticpro.data.model.RowsWithDescrip
import com.example.ratinadeticpro.data.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ResultViewModel @Inject constructor(private val repo: Repo) : ViewModel() {

    val mutableList = MutableLiveData<RowsWithDescrip>()
    val mutableError = MutableLiveData<String>()
    private lateinit var result: RowsWithDescrip
    private lateinit var resultBeforeDisrip: Rows

    fun getPost(id: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    resultBeforeDisrip = repo.findResultFromGoogleSheet().rows.last {
                        it.iduser == Integer.parseInt(id)
                    }


                    val date = getCurrentDateTime()
                    val dateInString = date.toString("yyyy/MM/dd")
                    val description = repo.getPredictResult(
                        PredictImgEntity(
                            ID_patient = id,
                            eye_part = "lift",
                            prediction = resultBeforeDisrip.type,
                            Probability = resultBeforeDisrip.probability.toString(),
                            date = dateInString
                        )

                    )
                    result = resultBeforeDisrip.mapWithDiscrip(description.Result)
                }

                mutableList.value = result
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
