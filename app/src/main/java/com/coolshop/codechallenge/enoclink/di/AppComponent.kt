package com.coolshop.codechallenge.enoclink.di

import com.coolshop.codechallenge.enoclink.EnocLinkApp
import com.coolshop.codechallenge.enoclink.login.di.LoginComponent
import com.coolshop.codechallenge.enoclink.login.di.LoginModule
import com.coolshop.codechallenge.enoclink.profile.di.ProfileComponent
import com.coolshop.codechallenge.enoclink.profile.di.ProfileModule
import com.coolshop.codechallenge.enoclink.splash.di.SplashComponent
import com.coolshop.codechallenge.enoclink.splash.di.SplashModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(enocLinkApp: EnocLinkApp)
    fun loginComponent(module: LoginModule) : LoginComponent
    fun splashComponent(module: SplashModule) : SplashComponent
    fun profileComponent(module: ProfileModule) : ProfileComponent
}