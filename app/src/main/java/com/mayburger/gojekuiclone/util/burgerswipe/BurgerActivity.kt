package com.mayburger.gojekuiclone.util.burgerswipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.util.ext.ViewUtils.yToDp
import kotlinx.android.synthetic.main.activity_burger.*

class BurgerActivity : AppCompatActivity() {

    val initialBunBottom = 0f
    val initialTomatoes = 32f
    val initialMeat2 = 58f
    val initialCheese = 86f
    val initialMeat1 = 112f
    val initialBunTop = 140f

    val endBunTop = 44f
    val endMeat1 = 16f
    val endMeat2 = -40f
    val endTomatoes = -66f
    val endBunBottom = -94f

    lateinit var swipeRefresh:SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_burger)

        bunTop.yToDp(initialBunTop, false)
        meat1.yToDp(initialMeat1, false)
        cheese.yToDp(initialCheese, false)
        meat2.yToDp(initialMeat2, false)
        tomatoes.yToDp(initialTomatoes, false)
        bunBottom.yToDp(initialBunBottom, false)

        swipeRefresh.setOnRefreshListener(object:SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {

            }
        })

        bunTop.yToDp(-10f, percent = 20f, onPercent = {
            meat1.yToDp(-10f, percent = 20f, onPercent = {
                cheese.yToDp(-10f, percent = 20f, onPercent = {
                    meat2.yToDp(-10f, percent = 20f, onPercent = {
                        tomatoes.yToDp(-10f, percent = 20f, onPercent = {
                            bunTop.yToDp(-10f, percent = 20f, onPercent = {
                                bunBottom.yToDp(-10f, onEnd = {
                                    delay(1000) {
                                        bunTop.yToDp(endBunTop)
                                        meat1.yToDp(endMeat1)
                                        meat2.yToDp(endMeat2)
                                        tomatoes.yToDp(endTomatoes)
                                        bunBottom.yToDp(endBunBottom,onEnd={
                                            favoriteContainer.yToDp(30f,percent=40f,onPercent = {
                                                favoriteContainer.yToDp(-1000f)
                                            })
                                        })
                                    }
                                })
                            })
                        })
                    })
                })
            })
        })
    }

    fun delay(delay: Long, runnable: () -> Unit) {
        Handler().postDelayed({
            runnable.invoke()
        }, delay)
    }

}