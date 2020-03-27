package com.example.ratinadeticpro.data.dagger.model

import androidx.lifecycle.ViewModel
import com.example.ratinadeticpro.Ui.ui.History.HistoryViewModel
import com.example.ratinadeticpro.Ui.ui.Login.LoginViewModel
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
}