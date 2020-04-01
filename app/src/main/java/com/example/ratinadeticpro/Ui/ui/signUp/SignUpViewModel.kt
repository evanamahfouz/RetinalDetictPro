package com.example.ratinadeticpro.Ui.ui.signUp


import android.util.Log
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

class SignUpViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    val mutableList = MutableLiveData<String>()
    val mutableError = MutableLiveData<String>()

    fun getPost(user: UserEntity) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    repo.insertToDB(
                        listOf(
                            WhatToDoEntity(
                                "CNV",
                                "Choroidal neovascularization is the creation of new blood vessels in the choroid layer of the eye."
                            ),
                            WhatToDoEntity(
                                "DME",
                                "Diabetic Macular Edema is a complication of diabetes caused by fluid accumulation in the macula that can affect the fovea."
                            ),
                            WhatToDoEntity(
                                "DRUSEN",
                                "Drusen are yellow deposits under the retina. Drusen are made up of lipids, a fatty protein."
                            ),
                            WhatToDoEntity(
                                "NORMAL",
                                "YOUR SAFE HOPE YOU STAY ALWAYS HEALTHY. "
                            )
                        )
                    )
                    repo.signUp(user)
                }
                mutableList.value = "Done Successfully"
            } catch (e: Exception) {
                Log.v("insideSignUpViewMode", e.message!!)
                mutableError.value = e.message
            }
        }
    }
}
