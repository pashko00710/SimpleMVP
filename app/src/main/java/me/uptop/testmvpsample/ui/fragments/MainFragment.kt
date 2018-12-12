package me.uptop.testmvpsample.ui.fragments

import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.view.View
import butterknife.BindView
import me.uptop.testmvpsample.R
import me.uptop.testmvpsample.TestSampleApp
import me.uptop.testmvpsample.dagger.components.FragmentComponent
import me.uptop.testmvpsample.dagger.modules.ActivityModule
import me.uptop.testmvpsample.dagger.modules.FragmentModule
import me.uptop.testmvpsample.mvp.presenters.MainPresenter
import me.uptop.testmvpsample.mvp.views.MainContract
import me.uptop.testmvpsample.ui.adapters.WeatherAdapter
import me.uptop.testmvpsample.ui.base.BaseFragment
import java.util.*
import javax.inject.Inject

class MainFragment : BaseFragment(), MainContract.View {
    @Inject
    lateinit var presenter: MainPresenter
    @BindView(R.id.view_pager)
    lateinit var pager: ViewPager

    var adapter: WeatherAdapter? = null
        private set

    override val layoutId: Int
        get() = R.layout.fragment_main

    override fun initView(view: View) {
        createDaggerComponent().inject(this)
        presenter.view = this
        initAdapter()
        presenter.getCitiesList()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun initAdapter() {
        adapter = WeatherAdapter(fragmentManager!!)
        pager.adapter = adapter
    }

    override fun showMessage(message: String) {
        super.showMessage(message)
    }

    override fun addItemToAdapter(item: String) {
        adapter!!.addItem(item)
    }

    private fun createDaggerComponent(): FragmentComponent {
        return TestSampleApp.application.component
                .addActivityComponent(ActivityModule(Objects.requireNonNull<FragmentActivity>(activity)))
                .addFragmentComponent(FragmentModule(this))
    }
}
