package com.coolshop.codechallenge.enoclink.splash.di

import com.coolshop.codechallenge.enoclink.di.ActivityScope
import com.coolshop.codechallenge.enoclink.splash.SplashActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [SplashModule::class])
interface SplashComponent {
    fun inject(activity: SplashActivity)
}