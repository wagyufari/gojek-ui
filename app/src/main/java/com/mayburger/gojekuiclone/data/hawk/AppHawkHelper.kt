package com.mayburger.gojekuiclone.data.hawk

import android.graphics.Bitmap
import com.orhanobut.hawk.Hawk
import javax.inject.Inject

class AppHawkHelper @Inject constructor() : HawkHelper {


    private val TAG = this.javaClass.simpleName

    companion object {
        private const val HAWK_KEY_IS_GRAB_CARD_LOCKED = "is_grab_card_locked"
    }

    override var isGrabCardLocked: Boolean
        get() = Hawk.get(HAWK_KEY_IS_GRAB_CARD_LOCKED,false)
        set(value) {
            Hawk.put(HAWK_KEY_IS_GRAB_CARD_LOCKED,value)
        }


}