package com.example.ratinadeticpro.Ui.ui.Profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratinadeticpro.data.Firebase.User
import com.example.ratinadeticpro.data.db.UserEntity
import com.example.ratinadeticpro.data.model.UserProfile
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


class ProfileViewModel @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val repo: Repo
) : ViewModel() {
    val mutableList = MutableLiveData<UserProfile>()
    val mutableError = MutableLiveData<String>()
    private lateinit var user: UserProfile
    private var count = 0

    fun getPost(id: String) {

        viewModelScope.launch {
            try {

                withContext(Dispatchers.IO) {
                    user = repo.getProfilrData(id)
                }
                mutableList.value = user
            } catch (e: Exception) {
                Log.v("insideProfileViewMode", e.message!!)
                mutableError.value = e.message
            }
        }
    }
}
