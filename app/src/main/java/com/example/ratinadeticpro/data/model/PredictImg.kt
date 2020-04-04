package com.example.ratinadeticpro.data.model

data class PredictImg(

    val id_patient: String = "",
    val eye_part: String = "",
    val prediction: String = "",

    val probability: String = "",
    val date: String = "",
    val gender: String = ""
    ,
    val age: String = ""

) {
    constructor() : this("", "", "", "", "", "", "")

}

