package com.example.ratinadeticpro.data.dagger.model

import android.content.Context
import android.content.SharedPreferences
import com.example.ratinadeticpro.App
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppContextBinding {


    @Binds
    abstract fun provideContext(app: App): Context


}