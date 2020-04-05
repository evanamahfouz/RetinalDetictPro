package com.example.ratinadeticpro.data.repo

import com.example.ratinadeticpro.data.db.PredictImgEntity
import com.example.ratinadeticpro.data.db.RetinaDetectDataBase
import com.example.ratinadeticpro.data.db.UserEntity
import com.example.ratinadeticpro.data.db.WhatToDoEntity
import com.example.ratinadeticpro.data.model.CountOfType
import com.example.ratinadeticpro.data.model.Json4Kotlin_Base
import com.example.ratinadeticpro.data.model.UserProfile
import com.example.ratinadeticpro.data.network.GoogleSheetAPI
import javax.inject.Inject


class Repo @Inject constructor(
    private val db: RetinaDetectDataBase,
    private val googleSheetAPI: GoogleSheetAPI
) {


    suspend fun signUp(user: UserEntity) {
        // handle signUp
        db.userDOA().insert(user)

    }


    suspend fun getCountForEachType(id: String): List<CountOfType> {
        return db.predictImgDOA().getCountForCharUser(id)

    }


    suspend fun login(id: String, pass: String): UserEntity {
        // handle login
        return db.userDOA().getUser(id, pass)
    }

    suspend fun getProfilrData(id: String): UserProfile {
        // handle login
        return UserProfile(
            db.userDOA().getUserProfile(id), db.predictImgDOA().getCountUser(id).toString()
        )
    }


    suspend fun getPredictResult(predictImgEntity: PredictImgEntity): WhatToDoEntity {
        // handle Save the prediction Result and display What To Do Next
        db.predictImgDOA().insert(predictImgEntity)

        return db.whatToDoDOA().getDiabetesTypeResult(predictImgEntity.prediction)

    }

    suspend fun getAllPredictio(Id: String): List<PredictImgEntity> {
        return db.predictImgDOA().getALLPrediction(Id)


    }

    suspend fun findResultFromGoogleSheet(): Json4Kotlin_Base {
        return googleSheetAPI.getResult()
    }


}