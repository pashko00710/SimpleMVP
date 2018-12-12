package me.uptop.testmvpsample.mvp.views

import me.uptop.testmvpsample.data.storage.realm.WeatherCityRealm
import me.uptop.testmvpsample.mvp.presenters.BasePresenter

interface CityWeatherContract {
    interface View : BaseView<Presenter> {
        fun showWeatherCity(weatherCityData: WeatherCityRealm)
    }

    interface Presenter : BasePresenter {

    }
}
