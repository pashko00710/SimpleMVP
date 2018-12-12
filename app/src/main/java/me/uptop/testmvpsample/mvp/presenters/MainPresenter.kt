package me.uptop.testmvpsample.mvp.presenters

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.realm.RealmResults
import me.uptop.testmvpsample.data.network.response.WeatherResponse
import me.uptop.testmvpsample.data.storage.realm.CityRealm
import me.uptop.testmvpsample.mvp.models.MainModelImpl
import me.uptop.testmvpsample.mvp.views.MainContract
import javax.inject.Inject

class MainPresenter @Inject constructor(val model: MainModelImpl) : AbstractPresenter<MainContract.View>(),
        MainContract.Presenter {
    private val TAG = javaClass.name

    @SuppressLint("CheckResult")
    override fun addCity(country: String) {
        val changedStandardCountry = changeStringByStandart(country)
        model.addCity(changedStandardCountry)
                .subscribeWith<DisposableObserver<WeatherResponse>>(object: DisposableObserver<WeatherResponse>() {
                    override fun onComplete() {}

                    override fun onNext(t: WeatherResponse) {
                        view?.addItemToAdapter(changedStandardCountry)
                        view?.showMessage("Страна удачно добавлена")
                    }

                    override fun onError(e: Throwable) {
                        //"Сделать нормальное отображение эксепшенов"
                        //  view?.showMessage(e.message.toString())
                        view?.showMessage("Город не может быть добавлен. Вы добавляете не существующий город или ранее добавленный")
                    }

                })
    }

    private fun changeStringByStandart(country: String): String {
        //можно было бы сделать extension функцию
        return country.substring(0, 1).toUpperCase() + country.substring(1).toLowerCase()
    }

    @SuppressLint("CheckResult")
    override fun getCitiesList() {
        model.cities
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith<DisposableObserver<RealmResults<CityRealm>>>(object : DisposableObserver<RealmResults<CityRealm>>() {
                    override fun onNext(cityRealm: RealmResults<CityRealm>) {
                        for (city in cityRealm) {
                            view?.addItemToAdapter(city.cityName!!)
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                    override fun onComplete() {
                        Log.e(TAG, "onComplete:")
                    }
                })

    }
}
