package me.uptop.testmvpsample.dagger.modules


import android.content.Context
import android.support.v4.app.Fragment

import dagger.Module
import dagger.Provides
import me.uptop.testmvpsample.dagger.FragmentContext

@Module
class FragmentModule(private val fragment: Fragment) {
    @Provides
    @FragmentContext
    internal fun providesContext(): Context {
        return fragment.context!!
    }
}
