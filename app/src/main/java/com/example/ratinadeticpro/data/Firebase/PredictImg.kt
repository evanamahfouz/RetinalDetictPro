package com.example.ratinadeticpro.data.Firebase

data class PredictImg(

    val id_patient: String = "",
    val eye_part: String = "",
    val prediction: String = "",

    val probability: String = "",
    val date: String = ""
) {
    constructor() : this("", "", "", "", "")

}

