package com.mayburger.gojekuiclone.util.textfont

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.ui.base.BaseApplication


/**
 * Created by rujul on 2/5/2016.
 */
class EditTextFont(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    init {
        val array = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.TextFont,
                0, 0)
        val typefaceType: Int
        val textString: String
        try {
            typefaceType = array.getInteger(R.styleable.EditTextFont_typeface, 0)
        } finally {
            array.recycle()
        }

        setCustomFont(typefaceType)
        includeFontPadding = false
    }

    fun setCustomFont(typefaceType: Int): Boolean {
        if (BaseApplication.getApp().getTypeFace(typefaceType) != null) {
            typeface = BaseApplication.getApp().getTypeFace(typefaceType)
            return true
        }
        return false
    }
}