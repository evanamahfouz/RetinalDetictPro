package com.example.ratinadeticpro.Ui.ui.signUp


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratinadeticpro.data.db.UserEntity
import com.example.ratinadeticpro.data.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    val mutableList = MutableLiveData<String>()
    val mutableError = MutableLiveData<String>()

    fun getPost(user: UserEntity) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    repo.signUp(user)
                }
                mutableList.value = "Done Successfully"
            } catch (e: Exception) {
                Log.v("insideSignUpViewMode",e.message!!)
                mutableError.value = e.message
            }
        }
    }
}
