package com.example.ratinadeticpro.data.Firebase

import com.google.firebase.database.Exclude

class CountEach(
    val NORMAL: String,
    val DRUSEN: String,
    val DME: String,
    val CNV: String
) {
    constructor() : this("", "", "", "")

}