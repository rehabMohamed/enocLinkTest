package com.coolshop.codechallenge.enoclink.profile.di

import com.coolshop.codechallenge.enoclink.data.UserRepository
import com.coolshop.codechallenge.enoclink.di.ActivityScope
import com.coolshop.codechallenge.enoclink.profile.ProfilePresenter
import com.coolshop.codechallenge.enoclink.profile.ProfilePresenterImp
import com.coolshop.codechallenge.enoclink.profile.ProfileView
import dagger.Module
import dagger.Provides

@Module
class ProfileModule {

    @Provides
    @ActivityScope
    fun providePresenter(userRepository: UserRepository): ProfilePresenter<ProfileView> {
        return ProfilePresenterImp(userRepository)
    }
}