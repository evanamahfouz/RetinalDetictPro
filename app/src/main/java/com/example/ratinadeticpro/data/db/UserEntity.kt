package com.example.ratinadeticpro.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_tb")
data class UserEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "ID_User") var ID_User: String = "",
    @ColumnInfo(name = "password") var password: String = "",
    @ColumnInfo(name = "email") var email: String = "",

    @ColumnInfo(name = "age") var age: String = "",
    @ColumnInfo(name = "gender") var gender: String = ""

)