package com.example.ratinadeticpro.data.dagger.component

import com.example.ratinadeticpro.App
import com.example.ratinadeticpro.data.dagger.model.ActivityBindingModule
import com.example.ratinadeticpro.data.dagger.model.AppModule
import com.example.ratinadeticpro.data.dagger.model.FragmentsBindings
import com.example.ratinadeticpro.data.dagger.model.ViewModelsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, FragmentsBindings::class, AndroidSupportInjectionModule::class, ViewModelsModule::class, ActivityBindingModule::class])
interface AppComponent {

    fun inject(app: App)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }


}