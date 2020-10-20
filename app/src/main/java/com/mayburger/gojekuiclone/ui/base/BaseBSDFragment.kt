package com.mayburger.gojekuiclone.ui.base

import android.app.Dialog
import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


abstract class BaseBSDFragment<T : ViewDataBinding, V : BaseViewModel<*>> :
    BottomSheetDialogFragment(),BaseNavigatorFragment{

    var baseActivity: BaseActivity<*, *>? = null
        private set
    private var mRootView: View? = null
    var viewDataBinding: T? = null
        private set
    private var mViewModel: V? = null

    abstract val bindingVariable: Int

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
        baseActivity?.showBottomSheet(fragment,tag)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = viewModel
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

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog =
            super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            val sheet =BottomSheetBehavior.from<FrameLayout?>(bottomSheet!!)
//            sheet.state = BottomSheetBehavior.STATE_EXPANDED
//            sheet.skipCollapsed
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.apply {
            setVariable(bindingVariable, mViewModel)
            executePendingBindings()
        }
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }

    override fun getContentResolver(): ContentResolver? = baseActivity?.contentResolver
}