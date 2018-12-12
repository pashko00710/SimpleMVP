package me.uptop.testmvpsample.mvp.presenters

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import me.uptop.testmvpsample.data.storage.realm.WeatherCityRealm
import me.uptop.testmvpsample.mvp.models.CityWeatherModelImpl
import me.uptop.testmvpsample.mvp.views.CityWeatherContract
import javax.inject.Inject

class CityWeatherPresenter @Inject constructor(val model: CityWeatherModelImpl) : AbstractPresenter<CityWeatherContract.View>(),
        CityWeatherContract.Presenter {
    private val TAG = javaClass.simpleName

    @SuppressLint("CheckResult")
    fun getAndShowWeatherCity(country: String) {
        model.getWeatherCityData(country)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith<DisposableObserver<WeatherCityRealm>>(object : DisposableObserver<WeatherCityRealm>() {
                    override fun onComplete() {}

                    override fun onNext(weatherCity: WeatherCityRealm) {
                        view?.showWeatherCity(weatherCity)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
    }
}