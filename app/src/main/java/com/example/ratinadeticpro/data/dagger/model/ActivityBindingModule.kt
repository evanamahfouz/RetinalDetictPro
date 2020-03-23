package com.example.ratinadeticpro.data.dagger.model

import com.example.ratinadeticpro.Ui.ui.Login.LoginActivity
import com.example.ratinadeticpro.Ui.ui.signUp.MainActivity


import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract fun bind(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindLogin(): LoginActivity
}