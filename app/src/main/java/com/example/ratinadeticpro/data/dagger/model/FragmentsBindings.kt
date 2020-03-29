package com.example.ratinadeticpro.data.dagger.model

import com.example.ratinadeticpro.Ui.ui.Chart.ChartFragment
import com.example.ratinadeticpro.Ui.ui.Dashboard_Main.DashBoradFrag
import com.example.ratinadeticpro.Ui.ui.History.HistoryFragment
import com.example.ratinadeticpro.Ui.ui.Profile.ProfileFragment
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

    @ContributesAndroidInjector
    abstract fun bindHisFrag(): HistoryFragment

    @ContributesAndroidInjector
    abstract fun bindProfileFrag(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun bindChartFrag(): ChartFragment

    @ContributesAndroidInjector
    abstract fun bindDashFrag(): DashBoradFrag
}