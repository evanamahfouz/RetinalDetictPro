package com.example.ratinadeticpro.data.dagger.model

import androidx.lifecycle.ViewModel
import com.example.ratinadeticpro.Ui.ui.Login.LoginViewModel
import com.example.ratinadeticpro.Ui.ui.ViewModelFactory.ViewModelKey
import com.example.ratinadeticpro.Ui.ui.signUp.SignUpViewModel
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
}