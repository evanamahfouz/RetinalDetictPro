package com.example.ratinadeticpro.data.dagger.model

import com.example.ratinadeticpro.ui.ui.chart.ChartFragment
import com.example.ratinadeticpro.ui.ui.reportsWithSearch.ChartByAgeFragment
import com.example.ratinadeticpro.ui.ui.chartByGender.ChartByGenderFragment
import com.example.ratinadeticpro.ui.ui.chartOverAllFragment.ChartOverAllFragment
import com.example.ratinadeticpro.ui.ui.dashboard_Main.DashBoardFrag
import com.example.ratinadeticpro.ui.ui.history.HistoryFragment
import com.example.ratinadeticpro.ui.ui.profile.ProfileFragment
import com.example.ratinadeticpro.ui.ui.resultFragment.ResultFragment
import com.example.ratinadeticpro.ui.ui.predict.PredictFragment
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
    abstract fun bindDashFrag(): DashBoardFrag

    @ContributesAndroidInjector
    abstract fun bindOverAllChartFrag(): ChartOverAllFragment

    @ContributesAndroidInjector
    abstract fun bindGenderChartFrag(): ChartByGenderFragment

    @ContributesAndroidInjector
    abstract fun bindAgeChartFrag(): ChartByAgeFragment
}