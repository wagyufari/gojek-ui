package com.mayburger.gojekuiclone.util.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

object SwipeRefreshBinding {

    @BindingAdapter("colorSchemeResources")
    @JvmStatic fun bindColorSchemeResources(layout: SwipeRefreshLayout, colors: IntArray?) {
        colors?.let { layout.setColorSchemeColors(*colors)}
    }

    @BindingAdapter("onRefreshListener")
    @JvmStatic fun bindOnRefreshListener(layout: SwipeRefreshLayout, onRefreshListener: SwipeRefreshLayout.OnRefreshListener?) {
        onRefreshListener?.let { layout.setOnRefreshListener(it) }
    }

    @BindingAdapter("enabled")
    @JvmStatic fun bindEnabled(layout: SwipeRefreshLayout, enabled: Boolean) {
        layout.isEnabled = enabled
    }

}
