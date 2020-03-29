package com.example.ratinadeticpro.Ui.ui.Chart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratinadeticpro.data.model.CountOfType
import com.example.ratinadeticpro.data.model.UserProfile
import com.example.ratinadeticpro.data.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class ChartViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    val mutableList = MutableLiveData<List<CountOfType>>()
    val mutableError = MutableLiveData<String>()
    private lateinit var result: List<CountOfType>

    fun getPost(id: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    result = repo.getCountForEachType(id)
                }
                mutableList.value = result
            } catch (e: Exception) {
                Log.v("insideSignUpViewMode", e.message!!)
                mutableError.value = e.message
            }
        }
    }

}
