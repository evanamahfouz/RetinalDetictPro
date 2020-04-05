package com.example.ratinadeticpro.ui.ui.history

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratinadeticpro.data.db.PredictImgEntity
import com.example.ratinadeticpro.data.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class HistoryViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    val mutableList = MutableLiveData<List<PredictImgEntity>>()
    val mutableError = MutableLiveData<String>()

    fun getPost(id: String) {
        viewModelScope.launch {
            try {
                val list = withContext(Dispatchers.IO) {
                    repo.getAllPredictio(id)
                }
                mutableList.value = list
                Log.v("SizeList1111",list.size.toString())
            } catch (e: Exception) {
                mutableError.value = e.message
            }
        }
    }
}
