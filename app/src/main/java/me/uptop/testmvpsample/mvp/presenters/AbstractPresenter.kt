package me.uptop.testmvpsample.mvp.presenters

import me.uptop.testmvpsample.mvp.views.BaseView

open class AbstractPresenter<T : BaseView<*>> {
    var view: T? = null

    fun detach() {
        view = null
    }
}
