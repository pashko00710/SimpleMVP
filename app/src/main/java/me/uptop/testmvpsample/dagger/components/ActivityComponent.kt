package me.uptop.testmvpsample.dagger.components

import dagger.Subcomponent
import me.uptop.testmvpsample.dagger.modules.ActivityModule
import me.uptop.testmvpsample.dagger.modules.FragmentModule
import me.uptop.testmvpsample.dagger.scopes.PerActivity
import me.uptop.testmvpsample.ui.activities.MainActivity

@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun addFragmentComponent(fragmentModule: FragmentModule): FragmentComponent

    fun inject(mainActivity: MainActivity)

}
