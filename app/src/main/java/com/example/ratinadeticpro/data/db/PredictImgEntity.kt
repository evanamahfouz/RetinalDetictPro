package com.example.ratinadeticpro.data.db


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "img_detect_tb",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = arrayOf("ID_User"),
            childColumns = arrayOf("ID_patient"),
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = WhatToDoEntity::class,
            parentColumns = arrayOf("Type"),
            childColumns = arrayOf("prediction"),
            onDelete = ForeignKey.CASCADE
        )]
)


data class PredictImgEntity(
    @PrimaryKey(autoGenerate = true)
    var ID_img: Int = 0,

    @ColumnInfo(name = "ID_patient") var ID_patient: String = "",
    @ColumnInfo(name = "eye_part") var eye_part: String = "",
    @ColumnInfo(name = "prediction") var prediction: String = "",

    @ColumnInfo(name = "Probability") var Probability: String = "",
    @ColumnInfo(name = "date") var date: String = ""
)

