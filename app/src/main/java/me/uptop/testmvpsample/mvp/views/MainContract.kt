package me.uptop.testmvpsample.mvp.views

import me.uptop.testmvpsample.mvp.presenters.BasePresenter

interface MainContract {

    interface View : BaseView<Presenter> {
        fun initAdapter()
        fun addItemToAdapter(cityName: String)
        fun showMessage(message: String)
    }

    interface Presenter : BasePresenter {
        fun getCitiesList()
        fun addCity(country: String)
    }
}
