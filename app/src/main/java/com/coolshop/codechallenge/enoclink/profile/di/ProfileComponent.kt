package com.coolshop.codechallenge.enoclink.profile.di

import com.coolshop.codechallenge.enoclink.di.ActivityScope
import com.coolshop.codechallenge.enoclink.profile.ProfileActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ProfileModule::class])
interface ProfileComponent {
    fun inject(activity: ProfileActivity)
}