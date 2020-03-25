package com.example.ratinadeticpro.data.model

data class Rows(

    val iduser: Int,
    val type: String,
    val probability: Double
) {
    fun mapWithDiscrip(discrip: String) =
        RowsWithDescrip(iduser, type, probability, discrip)

}