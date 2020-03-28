package com.example.ratinadeticpro.data.db


import androidx.room.*


@Dao
interface PredictImgDOA {
    @Query("SELECT * FROM img_detect_tb")
    suspend fun getPrediction(): List<PredictImgEntity>

    @Query("SELECT * FROM img_detect_tb WHERE ID_patient like :Id ")
    suspend fun getALLPrediction(Id: String): List<PredictImgEntity>


    @Insert
    suspend fun insert(predictImgEntity: PredictImgEntity)

    @Query("SELECT COUNT(*) FROM img_detect_tb")
    suspend fun getCount(): Int

    @Query("SELECT COUNT(*) FROM img_detect_tb where ID_patient like :id")
    suspend fun getCountUser(id: String): Int

    @Delete
    suspend fun delete(data: PredictImgEntity)
}