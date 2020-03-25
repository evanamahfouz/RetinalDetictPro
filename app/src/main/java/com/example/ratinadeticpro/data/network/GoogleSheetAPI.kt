package com.example.ratinadeticpro.data.network

import com.example.ratinadeticpro.data.model.Json4Kotlin_Base
import retrofit2.http.GET

interface GoogleSheetAPI {
    @GET("api?id=1jvAkJ6lBNqlOzYAyYuyScN7wg94jjXdeUz_6m1bPWeE")
    suspend fun getResult(): Json4Kotlin_Base
}