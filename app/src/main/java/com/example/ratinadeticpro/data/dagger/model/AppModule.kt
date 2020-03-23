package com.example.ratinadeticpro.data.dagger.model

import androidx.room.Room
import com.example.ratinadeticpro.App
import com.example.ratinadeticpro.data.db.RetinaDetectDataBase
import dagger.Module
import dagger.Provides
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
}
