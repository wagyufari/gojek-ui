package com.mayburger.gojekuiclone.ui.base

import android.graphics.Color
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.mayburger.gojekuiclone.data.DataManager
import com.mayburger.gojekuiclone.util.rx.RxBus
import com.mayburger.gojekuiclone.util.rx.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference


abstract class BaseViewModel<N:BaseNavigator>(
    val dataManager: DataManager,
    val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val TAG = "BaseViewModel"

    private lateinit var mNavigator: WeakReference<N?>
    var navigator: N?
        get() = mNavigator.get()
        set(navigator) {
            this.mNavigator = WeakReference(navigator)
        }

    var lifecycleOwner: LifecycleOwner? = null
    var colorSchemeResource = intArrayOf(Color.parseColor("#00A85F"))

    //it's must be inject from dagger
    val compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.add(
            RxBus.getDefault().toObservables()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ obj ->
                    onEvent(obj)
                }, { it.printStackTrace() })
        )
    }

    fun back(){
        navigator?.finishActivity()
    }

    abstract fun onEvent(obj: Any)

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
