package com.coolshop.codechallenge.enoclink.login.di

import com.coolshop.codechallenge.enoclink.login.LoginActivity
import com.coolshop.codechallenge.enoclink.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {
    fun inject(activity: LoginActivity)
}