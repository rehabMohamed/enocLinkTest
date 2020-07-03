package com.coolshop.codechallenge.enoclink.base.presenter

import com.coolshop.codechallenge.enoclink.base.view.BaseView

interface BasePresenter<V: BaseView> {

    fun onAttach(view: V?)

    fun onDetach()
}