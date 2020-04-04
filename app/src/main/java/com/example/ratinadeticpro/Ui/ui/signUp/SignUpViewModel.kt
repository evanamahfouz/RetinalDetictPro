package com.example.ratinadeticpro.Ui.ui.signUp


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratinadeticpro.data.Firebase.User
import com.example.ratinadeticpro.data.db.UserEntity

import com.example.ratinadeticpro.data.repo.Repo
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val repo: Repo
) : ViewModel() {
    val mutableList = MutableLiveData<String>()
    val mutableError = MutableLiveData<String>()

    fun getPost(user: User) {
        viewModelScope.launch {

            try {
                withContext(Dispatchers.IO) {
                    repo.signUp(
                        UserEntity(
                            user.id_User,
                            user.password,
                            user.email,
                            user.age,
                            user.gender
                        )
                    )
                }


                mutableList.value = "Done Successfully"
            } catch (e: Exception) {
                Log.v("insideSignUpViewMode", e.message!!)
                mutableError.value = e.message
            }
        }
    }
}
