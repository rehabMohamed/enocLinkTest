package com.coolshop.codechallenge.enoclink.login.di

import com.coolshop.codechallenge.enoclink.data.UserRepository
import com.coolshop.codechallenge.enoclink.login.LoginPresenter
import com.coolshop.codechallenge.enoclink.login.LoginPresenterImp
import com.coolshop.codechallenge.enoclink.login.LoginView
import com.coolshop.codechallenge.enoclink.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    @ActivityScope
    fun providePresenter(userRepository: UserRepository): LoginPresenter<LoginView> {
        return LoginPresenterImp(userRepository)
    }
}