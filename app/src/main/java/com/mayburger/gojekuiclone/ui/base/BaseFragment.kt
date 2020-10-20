package com.mayburger.gojekuiclone.ui.base

import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController


abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment(),
    BaseNavigatorFragment {

    var baseActivity: BaseActivity<*, *>? = null
        private set
    private var mRootView: View? = null
    var viewDataBinding: T? = null
        private set

    abstract val bindingVariable: Int

    lateinit var navController: NavController

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModel: V

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            val activity = context as BaseActivity<*, *>?
            this.baseActivity = activity
            activity?.onFragmentAttached()
        }
    }

    override fun showBottomSheet(fragment: BaseFragment<*, *>, tag: String) {
        (requireActivity() as BaseActivity<*,*>).showBottomSheet(fragment,tag)
    }

    fun requireAppActivity():AppCompatActivity{
        return requireActivity() as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mRootView = viewDataBinding!!.root
        return mRootView
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun finishActivity() {
        baseActivity?.finishActivity()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.apply {
            setVariable(bindingVariable, viewModel)
            executePendingBindings()
        }
    }

    override fun hideLoading() {
        baseActivity?.hideLoading()
    }


    override fun showLoading() {
        baseActivity?.showLoading()
    }

    override fun onError(message: String?) {
        baseActivity?.onError(message)
    }

    override fun showSnackBar(message: String) {
        baseActivity?.showSnackBar(message)
    }


    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }

    override fun getContentResolver(): ContentResolver? = baseActivity?.contentResolver
}