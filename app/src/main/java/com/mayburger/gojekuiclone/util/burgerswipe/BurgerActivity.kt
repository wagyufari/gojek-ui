package com.mayburger.gojekuiclone.util.burgerswipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.util.ext.ViewUtils.animToY
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

        bunTop.animToY(initialBunTop, false)
        meat1.animToY(initialMeat1, false)
        cheese.animToY(initialCheese, false)
        meat2.animToY(initialMeat2, false)
        tomatoes.animToY(initialTomatoes, false)
        bunBottom.animToY(initialBunBottom, false)

        swipeRefresh.setOnRefreshListener(object:SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {

            }
        })

        bunTop.animToY(-10f, percent = 20f, onPercent = {
            meat1.animToY(-10f, percent = 20f, onPercent = {
                cheese.animToY(-10f, percent = 20f, onPercent = {
                    meat2.animToY(-10f, percent = 20f, onPercent = {
                        tomatoes.animToY(-10f, percent = 20f, onPercent = {
                            bunTop.animToY(-10f, percent = 20f, onPercent = {
                                bunBottom.animToY(-10f, onEnd = {
                                    delay(1000) {
                                        bunTop.animToY(endBunTop)
                                        meat1.animToY(endMeat1)
                                        meat2.animToY(endMeat2)
                                        tomatoes.animToY(endTomatoes)
                                        bunBottom.animToY(endBunBottom,onEnd={
                                            favoriteContainer.animToY(30f,percent=40f,onPercent = {
                                                favoriteContainer.animToY(-1000f)
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