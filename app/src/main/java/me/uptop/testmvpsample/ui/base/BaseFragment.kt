package me.uptop.testmvpsample.ui.base

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.ButterKnife

abstract class BaseFragment : Fragment() {

    protected abstract val layoutId: Int
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)
        ButterKnife.bind(this, view)
        initView(view)

        return view
    }

    protected abstract fun initView(view: View)

    open fun showMessage(message: String) {
        Snackbar.make(view!!, message, Toast.LENGTH_SHORT).show()
    }
}
