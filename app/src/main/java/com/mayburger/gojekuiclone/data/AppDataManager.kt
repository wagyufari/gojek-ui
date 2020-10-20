package com.mayburger.gojekuiclone.data

import android.content.Context
import com.mayburger.gojekuiclone.data.hawk.HawkHelper
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppDataManager @Inject constructor(
    private val mContext: Context,
    private val mHawkHelper: HawkHelper
) : DataManager {

}