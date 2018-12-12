package me.uptop.testmvpsample.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import me.uptop.testmvpsample.R
import me.uptop.testmvpsample.TestSampleApp
import me.uptop.testmvpsample.dagger.components.FragmentComponent
import me.uptop.testmvpsample.dagger.modules.ActivityModule
import me.uptop.testmvpsample.dagger.modules.FragmentModule
import me.uptop.testmvpsample.data.storage.realm.WeatherCityRealm
import me.uptop.testmvpsample.mvp.presenters.CityWeatherPresenter
import me.uptop.testmvpsample.mvp.views.CityWeatherContract
import me.uptop.testmvpsample.ui.base.BaseFragment
import java.util.*
import javax.inject.Inject

class CityWeatherFragment : BaseFragment(), CityWeatherContract.View {
    @Inject
    lateinit var presenter: CityWeatherPresenter
    @BindView(R.id.image_background)
    lateinit var backgroundImageView: ImageView
    @BindView(R.id.country)
    lateinit var countryTextView: TextView
    @BindView(R.id.base)
    lateinit var base: TextView
    @BindView(R.id.visibility)
    lateinit var visibility: TextView
    @BindView(R.id.clouds)
    lateinit var clouds: TextView

    override val layoutId: Int
        get() = R.layout.fragment_city_weather //To change initializer of created properties use File | Settings | File Templates.

    override fun initView(view: View) {
        createDaggerComponent().inject(this)
        presenter.view = this
        val bundle = this.arguments
        val country = bundle?.getString(DATA, "")
        countryTextView.text = country

        Glide
                .with(this)
                .load("https://marwendoukh.files.wordpress.com/2017/01/clouds-wallpaper2.jpg")
                .into(backgroundImageView)

        presenter.getAndShowWeatherCity(country!!)
    }

    @SuppressLint("SetTextI18n")
    override fun showWeatherCity(weatherCityData: WeatherCityRealm) {
        //Поменять реализацию с отображения здесь на recycler с разными типами для динамической и легковесной подгрузки
        // P.S. схалявил на этом куске
        base.text = "Base ${weatherCityData.base}"
        visibility.text = "Visibility ${weatherCityData.visibility}"
        clouds.text = "Clouds ${weatherCityData.clouds?.all.toString()}"
    }

    companion object {
        private const val DATA = "city_weather_data"

        fun newInstance(country: String): Fragment {
            val pageFragment = CityWeatherFragment()
            val arguments = Bundle()
            arguments.putString(DATA, country)
            pageFragment.arguments = arguments
            return pageFragment
        }
    }

    private fun createDaggerComponent(): FragmentComponent {
        return TestSampleApp.application.component
                .addActivityComponent(ActivityModule(Objects.requireNonNull<FragmentActivity>(activity)))
                .addFragmentComponent(FragmentModule(this))
    }
}
