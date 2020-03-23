package com.example.ratinadeticpro.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "what_to_do_tb"
)


data class WhatToDoEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "Type") var Type: String = "",
    @ColumnInfo(name = "Result") var Result: String = ""

)

