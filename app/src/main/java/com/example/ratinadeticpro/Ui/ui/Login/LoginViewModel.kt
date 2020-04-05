package com.example.ratinadeticpro.ui.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratinadeticpro.data.db.UserEntity
import com.example.ratinadeticpro.data.repo.Repo
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val repo: Repo
) : ViewModel() {

    val mutableList = MutableLiveData<UserEntity>()
    val mutableError = MutableLiveData<String>()


    fun getPost(id: String, pass: String) {
        viewModelScope.launch {
            try {
                mutableList.value = repo.login(id, pass)
            } catch (e: Exception) {
                mutableError.value = e.message
            }
        }
    }
}