package com.mayburger.gojekuiclone.data

import android.content.Context
import android.graphics.Bitmap
import com.mayburger.gojekuiclone.data.hawk.HawkHelper
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppDataManager @Inject constructor(
    private val mContext: Context,
    private val mHawkHelper: HawkHelper
) : DataManager {
    override var isGrabCardLocked: Boolean
        get() = mHawkHelper.isGrabCardLocked
        set(value) {
            mHawkHelper.isGrabCardLocked = value
        }

}