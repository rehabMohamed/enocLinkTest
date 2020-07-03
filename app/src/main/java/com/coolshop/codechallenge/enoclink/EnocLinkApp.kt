package com.coolshop.codechallenge.enoclink

import android.app.Application
import com.coolshop.codechallenge.enoclink.di.AppComponent
import com.coolshop.codechallenge.enoclink.di.AppModule
import com.coolshop.codechallenge.enoclink.di.DaggerAppComponent

class EnocLinkApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
        appComponent.inject(this)
    }
}