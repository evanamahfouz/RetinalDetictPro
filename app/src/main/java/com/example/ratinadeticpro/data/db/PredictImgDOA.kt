package com.example.ratinadeticpro.data.db


import androidx.room.*


@Dao
interface PredictImgDOA {
    @Query("SELECT * FROM img_detect_tb")
    suspend fun getPrediction(): PredictImgEntity


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(predictImgEntity: PredictImgEntity)

    @Query("SELECT COUNT(*) FROM img_detect_tb")
    suspend fun getCount(): Int

    @Delete
    suspend fun delete(data: PredictImgEntity)
}