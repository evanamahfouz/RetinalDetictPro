package com.example.ratinadeticpro.data.db

import androidx.room.*
import com.example.ratinadeticpro.data.model.User


@Dao
interface UserDOA {
    @Query("SELECT * FROM user_tb")
    suspend fun getAllUser(): List<UserEntity>

    @Query("SELECT * FROM user_tb where ID_User like :id and password like :pass")
    suspend fun getUser(id: String, pass: String): UserEntity

    @Query("SELECT * FROM user_tb where ID_User like :id")
    suspend fun getUserProfile(id: String): UserEntity


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Query("SELECT COUNT(*) FROM user_tb")
    suspend fun getCount(): Int

    @Delete
    suspend fun delete(data: UserEntity)
}