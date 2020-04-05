package com.example.ratinadeticpro.data.fireBase

open class User(
    val id_User: String,
    val password: String,
    val email: String,
    val age: String,
    val gender: String
) {
    constructor() : this("", "", "", "", "")
}



