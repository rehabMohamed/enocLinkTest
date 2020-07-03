package com.coolshop.codechallenge.enoclink.base.presenter

import com.coolshop.codechallenge.enoclink.base.view.BaseView
import com.coolshop.codechallenge.enoclink.data.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


abstract class BasePresenterImp<V : BaseView>(open val userRepository: UserRepository) : BasePresenter<V>, CoroutineScope {

    var view: V? = null

    private val job: Job by lazy {
        Job()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun onDetach() {
        view = null
        job.cancel()
    }
}