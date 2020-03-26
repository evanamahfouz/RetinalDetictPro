package com.example.ratinadeticpro.data.dagger.model

import com.example.ratinadeticpro.Ui.ui.RsultFragment.ResultFragment
import com.example.ratinadeticpro.Ui.ui.predict.PredictFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBindings {
    @ContributesAndroidInjector
    abstract fun bind(): ResultFragment

    @ContributesAndroidInjector
    abstract fun bindFrag(): PredictFragment
}