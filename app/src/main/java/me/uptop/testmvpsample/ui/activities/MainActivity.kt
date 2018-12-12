package me.uptop.testmvpsample.ui.activities

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import me.uptop.testmvpsample.R
import me.uptop.testmvpsample.TestSampleApp
import me.uptop.testmvpsample.dagger.components.ActivityComponent
import me.uptop.testmvpsample.dagger.modules.ActivityModule
import me.uptop.testmvpsample.mvp.presenters.MainPresenter
import me.uptop.testmvpsample.ui.base.BaseActivity
import me.uptop.testmvpsample.ui.fragments.MainFragment
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var presenter: MainPresenter
    lateinit var toolbar: Toolbar

    lateinit var fragment: MainFragment
    private var nameCountryEditText: TextInputEditText? = null

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createDaggerComponent().inject(this)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        Objects.requireNonNull<ActionBar>(supportActionBar).setDisplayShowTitleEnabled(false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }

        fragment = MainFragment()
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val backStateName = fragment.javaClass.name

        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)

        if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) { //fragment not in back stack, create it.
            val ft = manager.beginTransaction()
            ft.replace(R.id.fragment_container, fragment, backStateName)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ft.addToBackStack(backStateName)
            ft.commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_country_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val actionBar = supportActionBar
        when (item.itemId) {
            R.id.add_country ->

                addCountry()
        }
        return true
    }

    private fun addCountry() {
        val dialogBuilder = AlertDialog.Builder(this)

        val dialogLayout = layoutInflater.inflate(R.layout.dialog_edit_country, null)
        nameCountryEditText = dialogLayout.findViewById(R.id.name_country)

        dialogBuilder.setTitle(R.string.add_country)
                .setMessage(getString(R.string.add_country_message))
                .setView(dialogLayout)
                .setPositiveButton(R.string.country_ok) { dialogInterface, i ->
                    fragment.presenter.addCity(nameCountryEditText!!.text.toString())
                    dialogInterface.dismiss()
                }
                .setNegativeButton(R.string.country_cancel) { dialogInterface, i -> dialogInterface.dismiss() }
                .setOnDismissListener { dialogInterface -> fragment.adapter!!.notifyDataSetChanged() }
                .show()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun createDaggerComponent(): ActivityComponent {
        return TestSampleApp.application.component
                .addActivityComponent(ActivityModule(this))
    }
}
