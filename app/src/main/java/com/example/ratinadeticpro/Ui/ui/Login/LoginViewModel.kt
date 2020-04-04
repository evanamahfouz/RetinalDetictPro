package com.example.ratinadeticpro.Ui.ui.Login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratinadeticpro.data.Firebase.User
import com.example.ratinadeticpro.data.db.UserEntity
import com.example.ratinadeticpro.data.db.WhatToDoEntity
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
//firebaseDatabase.getReference("users").orderByChild("id_User").equalTo(id)
//.addValueEventListener(object : ValueEventListener {
//    override fun onCancelled(p0: DatabaseError) {
//    }
//
//    override fun onDataChange(p0: DataSnapshot) {
//        for (data in p0.children) {
//            data.getValue(User::class.java)?.also {
//                if (it.id_User == id && it.password == pass
//                ) {
//                    mutableList.value = it
//
//                }
//            }
//
//        }
//    }
//
//})