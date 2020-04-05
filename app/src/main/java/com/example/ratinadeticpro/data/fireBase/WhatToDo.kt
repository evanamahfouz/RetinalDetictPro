package com.example.ratinadeticpro.data.fireBase

open class WhatToDo(
    val id: String,
    val Type: String,
    val Result: String,
    val count: String

) {
    constructor() : this("", "", "", "")
}