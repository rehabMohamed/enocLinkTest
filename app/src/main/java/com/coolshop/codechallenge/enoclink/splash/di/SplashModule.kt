package com.coolshop.codechallenge.enoclink.splash.di

import com.coolshop.codechallenge.enoclink.data.UserRepository
import com.coolshop.codechallenge.enoclink.di.ActivityScope
import com.coolshop.codechallenge.enoclink.splash.SplashPresenter
import com.coolshop.codechallenge.enoclink.splash.SplashPresenterImp
import com.coolshop.codechallenge.enoclink.splash.SplashView
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    @ActivityScope
    fun providePresenter(userRepository: UserRepository): SplashPresenter<SplashView> {
        return SplashPresenterImp(userRepository)
    }
}