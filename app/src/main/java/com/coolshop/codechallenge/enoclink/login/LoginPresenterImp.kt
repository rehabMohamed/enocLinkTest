package com.coolshop.codechallenge.enoclink.login

import com.coolshop.codechallenge.enoclink.base.presenter.BasePresenterImp
import com.coolshop.codechallenge.enoclink.data.UserRepository
import com.coolshop.codechallenge.enoclink.data.network.model.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginPresenterImp<V: LoginView> @Inject constructor(override val userRepository: UserRepository)
    : BasePresenterImp<V>(userRepository), LoginPresenter<V> {

    private var emailValid = false
    private var passwordValid = false

    override fun login(email: String, password: String) {
        launch(coroutineContext) {
            val response = withContext(Dispatchers.IO){
                userRepository.login(email, password)
            }
            when(response) {
                is Response.Success<String> -> view?.openProfileScreen()
                is Response.Error ->  view?.showLoginFailed()
            }
        }
    }

    override fun emailChanged(email: String) {
        emailValid = if (email.isEmailValid()) {
            view?.hideInvalidEmail()
            true
        } else {
            view?.showInvalidEmail()
            false
        }
        loginDataChanged()
    }

    override fun passwordChanged(password: String) {
        passwordValid = if (password.isPasswordValid()) {
            view?.hideInvalidPassword()
            true
        } else {
            view?.showInvalidPassword()
            false
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
