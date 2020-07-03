package com.coolshop.codechallenge.enoclink.login

import com.coolshop.codechallenge.enoclink.base.presenter.BasePresenterImp
import com.coolshop.codechallenge.enoclink.data.UserRepository
import com.coolshop.codechallenge.enoclink.data.network.model.Response
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginPresenterImp<V: LoginView> @Inject constructor(override val userRepository: UserRepository)
    : BasePresenterImp<V>(userRepository), LoginPresenter<V> {

    var emailValid = false
    var passwordValid = false

    override fun login(email: String, password: String) {
        launch(coroutineContext) {
            when(userRepository.login(email, password)) {
                is Response.Success<String> -> view?.openProfileScreen()
                is Response.Error ->  view?.showLoginFailed()
            }
        }
    }

    override fun emailChanged(email: String) {
        if (email.isEmailValid()) {
            view?.hideInvalidEmail()
            emailValid = true
        } else {
            view?.showInvalidEmail()
            emailValid = false
        }
        loginDataChanged()
    }

    override fun passwordChanged(password: String) {
        if (password.isPasswordValid()) {
            view?.hideInvalidPassword()
            passwordValid = true
        } else {
            view?.showInvalidPassword()
            passwordValid = false
        }
        loginDataChanged()
    }

    private fun loginDataChanged() {
        if (emailValid && passwordValid) {
            view?.enableLogin()
        } else {
            view?.disableLogin()
        }
    }
}
