package com.example.ratinadeticpro.data.db


import androidx.room.*


@Dao
interface WhatToDoDOA {
    @Query("SELECT * FROM what_to_do_tb where Type like :type LIMIT 1")
    suspend fun getDiabetesTypeResult(type: String): WhatToDoEntity


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(whatToDoEntity: List<WhatToDoEntity>)

    @Query("SELECT COUNT(*) FROM what_to_do_tb")
    suspend fun getCount(): Int

    @Delete
    suspend fun delete(whatToDoEntity: WhatToDoEntity)
}