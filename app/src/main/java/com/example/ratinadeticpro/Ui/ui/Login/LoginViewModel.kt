package com.example.ratinadeticpro.Ui.ui.Login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratinadeticpro.data.db.UserEntity
import com.example.ratinadeticpro.data.db.WhatToDoEntity
import com.example.ratinadeticpro.data.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repo: Repo) : ViewModel() {

    val mutableList = MutableLiveData<UserEntity>()
    val mutableError = MutableLiveData<String>()
    private lateinit var result: UserEntity


    fun getPost(id: String, pass: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    result = repo.login(id, pass)

                }
                mutableList.value = result
            } catch (e: Exception) {
                mutableError.value = e.message
            }
        }
    }
}
