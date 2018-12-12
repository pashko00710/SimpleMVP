package me.uptop.testmvpsample.dagger.components

import dagger.Subcomponent
import me.uptop.testmvpsample.dagger.modules.FragmentModule
import me.uptop.testmvpsample.dagger.scopes.PerFragment
import me.uptop.testmvpsample.ui.fragments.CityWeatherFragment
import me.uptop.testmvpsample.ui.fragments.MainFragment

@PerFragment
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(fragment: MainFragment)
    fun inject(fragment: CityWeatherFragment)
}
