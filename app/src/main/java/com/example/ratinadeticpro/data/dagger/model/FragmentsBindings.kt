package com.example.ratinadeticpro.data.dagger.model

import com.example.ratinadeticpro.Ui.ui.RsultFragment.ResultFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBindings {
    @ContributesAndroidInjector
    abstract fun bind(): ResultFragment
}