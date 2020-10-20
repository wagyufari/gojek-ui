package com.mayburger.gojekuiclone.util.binding

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mayburger.gojekuiclone.util.StringProvider

object TextViewBinding {

    @BindingAdapter("maxLines")
    @JvmStatic
    fun setMaxlines(view: TextView, maxlines: Int) {
        view.post {
            if (view.text != null) {
                view.maxLines = maxlines
            }
            Int.MAX_VALUE
        }
    }

    @BindingAdapter("nameAbbreviation")
    @JvmStatic
    fun setNameAbbreviation(view: TextView, _name: String) {
        if (_name.split(" ").size > 1) {
            view.text = "${_name.split(" ")[0][0]}${_name.split(" ")[1][0]}"
        } else {
            view.text = "${_name.split(" ")[0][0]}"
        }
    }


    @BindingAdapter("textLocale")
    @JvmStatic
    fun bindTextView(view: TextView, str: String) {
        view.text = StringProvider.getInstance().getString(str)
    }

    @BindingAdapter("hintLocale")
    @JvmStatic
    fun bindHint(view:EditText, str:String){
        view.hint = StringProvider.getInstance().getString(str)
    }

}