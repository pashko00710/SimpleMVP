package me.uptop.testmvpsample.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import me.uptop.testmvpsample.ui.fragments.CityWeatherFragment
import java.util.*

class WeatherAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val items: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return CityWeatherFragment.newInstance(items[position])
    }

    fun addItem(item: String) {
        items.add(item)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return items.size
    }
}
