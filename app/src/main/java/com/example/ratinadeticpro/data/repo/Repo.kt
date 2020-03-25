package com.example.ratinadeticpro.data.repo

import com.example.ratinadeticpro.data.db.PredictImgEntity
import com.example.ratinadeticpro.data.db.RetinaDetectDataBase
import com.example.ratinadeticpro.data.db.UserEntity
import com.example.ratinadeticpro.data.db.WhatToDoEntity
import com.example.ratinadeticpro.data.model.Json4Kotlin_Base
import com.example.ratinadeticpro.data.network.GoogleSheetAPI
import javax.inject.Inject

/**
 * Class that requests authentication and user information from the remote com.example.ratinadeticpro.data.model.getData source and
 * maintains an in-memory cache of signUp status and user credentials information.
 */

class Repo @Inject constructor(
    private val db: RetinaDetectDataBase,
    private val googleSheetAPI: GoogleSheetAPI
) {


    suspend fun signUp(user: UserEntity) {
        // handle signUp
        db.userDOA().insert(user)

    }
    suspend fun insertToDB(whatToDoEntity: List<WhatToDoEntity>) {
        // handle signUp
        db.whatToDoDOA().insert(whatToDoEntity)

    }


    suspend fun login(id: String, pass: String): UserEntity {
        // handle login
        return db.userDOA().getUser(id, pass)
    }


    suspend fun getPredictResult(predictImgEntity: PredictImgEntity): WhatToDoEntity {
        // handle Save the prediction Result and display What To Do Next
        db.predictImgDOA().insert(predictImgEntity)

        return db.whatToDoDOA().getDiabetesTypeResult(predictImgEntity.prediction)

    }

    suspend fun findResultFromGoogleSheet(): Json4Kotlin_Base {
        return googleSheetAPI.getResult()
    }


}