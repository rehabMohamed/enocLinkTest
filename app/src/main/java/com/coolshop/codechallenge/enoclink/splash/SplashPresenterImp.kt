package com.coolshop.codechallenge.enoclink.splash

import com.coolshop.codechallenge.enoclink.base.presenter.BasePresenterImp
import com.coolshop.codechallenge.enoclink.data.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashPresenterImp<V: SplashView> @Inject constructor(override val userRepository: UserRepository)
    : BasePresenterImp<V>(userRepository), SplashPresenter<V> {

    override fun checkLogin() {

        launch(coroutineContext) {
            delay(3000)
            if (userRepository.isLoggedIn()) {
                view?.openProfileScreen()
            } else {
                view?.openLoginScreen()
            }
        }
    }
}