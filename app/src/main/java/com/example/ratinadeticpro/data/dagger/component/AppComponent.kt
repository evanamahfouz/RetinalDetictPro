package com.example.ratinadeticpro.data.dagger.component

import com.example.ratinadeticpro.App
import com.example.ratinadeticpro.data.dagger.model.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import android.content.SharedPreferences


@Singleton
@Component(modules = [AppModule::class, AppContextBinding::class, FragmentsBindings::class, AndroidSupportInjectionModule::class, ViewModelsModule::class, ActivityBindingModule::class])
interface AppComponent {

    fun inject(app: App)
    fun sharedPreferences(): SharedPreferences


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }


}