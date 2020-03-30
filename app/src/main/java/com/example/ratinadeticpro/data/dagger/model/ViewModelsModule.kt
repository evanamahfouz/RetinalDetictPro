package com.example.ratinadeticpro.data.dagger.model

import androidx.lifecycle.ViewModel
import com.example.ratinadeticpro.Ui.ui.Chart.ChartViewModel
import com.example.ratinadeticpro.Ui.ui.ChartByAge.ChartByAgeViewModel
import com.example.ratinadeticpro.Ui.ui.ChartByGender.ChartByGenderViewModel
import com.example.ratinadeticpro.Ui.ui.ChartOverAllFragment.ChartOverAllViewModel
import com.example.ratinadeticpro.Ui.ui.History.HistoryViewModel
import com.example.ratinadeticpro.Ui.ui.Login.LoginViewModel
import com.example.ratinadeticpro.Ui.ui.Profile.ProfileViewModel
import com.example.ratinadeticpro.Ui.ui.RsultFragment.ResultViewModel
import com.example.ratinadeticpro.Ui.ui.ViewModelFactory.ViewModelKey
import com.example.ratinadeticpro.Ui.ui.predict.PredictViewModel
import com.example.ratinadeticpro.Ui.ui.signUp.SignUpViewModel
import com.example.ratinadeticpro.data.db.PredictImgEntity
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun provideSignUpViewModel(viewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun provideLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResultViewModel::class)
    abstract fun provideResultViewModel(viewModel: ResultViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PredictViewModel::class)
    abstract fun providePredictViewModel(viewModel: ResultViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    abstract fun provideHistoryViewModel(viewModel: HistoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun provideProfiletoryViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChartViewModel::class)
    abstract fun provideChartViewModel(viewModel: ChartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChartOverAllViewModel::class)
    abstract fun provideChartOverAllViewModel(viewModel: ChartOverAllViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChartByGenderViewModel::class)
    abstract fun provideChartByGenderViewModel(viewModel: ChartByGenderViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChartByAgeViewModel::class)
    abstract fun provideChartByAgeViewModel(viewModel: ChartByAgeViewModel): ViewModel
}