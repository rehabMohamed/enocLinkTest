package com.coolshop.codechallenge.enoclink.login

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import com.coolshop.codechallenge.enoclink.EnocLinkApp.Companion.appComponent
import com.coolshop.codechallenge.enoclink.R
import com.coolshop.codechallenge.enoclink.base.view.BaseActivity
import com.coolshop.codechallenge.enoclink.login.di.LoginModule
import com.coolshop.codechallenge.enoclink.profile.ProfileActivity
import com.coolshop.codechallenge.enoclink.utils.afterTextChanged
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginView {

    @Inject
    lateinit var presenter: LoginPresenter<LoginView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        presenter.onAttach(this)

        username.afterTextChanged {
            presenter.emailChanged(username.text.toString())
        }

        password.apply {
            afterTextChanged {
                presenter.passwordChanged(password.text.toString())
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        login()
                }
                false
            }
        }

        login.setOnClickListener {
            login()
        }
    }

    private fun login() {
        loading.visibility = VISIBLE
        presenter.login(
                username.text.toString(),
                password.text.toString()
        )
    }

    override fun performDI() {
        appComponent.loginComponent(LoginModule()).inject(this)
    }

    override fun showInvalidEmail() {
        invalidEmail.visibility = VISIBLE
    }

    override fun showInvalidPassword() {
        invalidPassword.visibility = VISIBLE
    }

    override fun hideInvalidEmail() {
        invalidEmail.visibility = GONE
    }

    override fun hideInvalidPassword() {
        invalidPassword.visibility = GONE
    }

    override fun enableLogin() {
        login.isEnabled = true
    }

    override fun disableLogin() {
        login.isEnabled = false
    }

    override fun showLoginFailed() {
        showToast(R.string.login_failed)
    }

    override fun openProfileScreen() {
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
