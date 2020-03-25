package com.example.ratinadeticpro.data.dagger.model

import androidx.room.Room
import com.example.ratinadeticpro.App
import com.example.ratinadeticpro.data.db.RetinaDetectDataBase
import com.example.ratinadeticpro.data.network.GoogleSheetAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module

class AppModule {

    @Provides
    @Singleton
    fun provideUserDataBase(): RetinaDetectDataBase {

        return Room.databaseBuilder(
            App.application().applicationContext, RetinaDetectDataBase::class.java,
            "user.db"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

    }

    @Singleton
    @Provides
    fun provideApi(): GoogleSheetAPI {


        val retrofit =
            Retrofit.Builder().baseUrl("http://gsx2json.com/").addConverterFactory(
                GsonConverterFactory.create()
            )
                .build()
        return retrofit.create(GoogleSheetAPI::class.java)
    }
}
