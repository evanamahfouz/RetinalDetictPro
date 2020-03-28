package com.example.ratinadeticpro.data.model

import com.example.ratinadeticpro.data.db.UserEntity

data class UserProfile(
    val userEntity: UserEntity,
    val countUser: String
)
