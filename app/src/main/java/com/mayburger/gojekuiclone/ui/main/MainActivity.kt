package com.mayburger.gojekuiclone.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mayburger.gojekuiclone.BR
import com.mayburger.gojekuiclone.ui.food.FoodActivity
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.databinding.ActivityMainBinding
import com.mayburger.gojekuiclone.models.events.TransitionEvent
import com.mayburger.gojekuiclone.ui.adapters.TabPagerAdapter
import com.mayburger.gojekuiclone.ui.base.BaseActivity
import com.mayburger.gojekuiclone.ui.main.fragments.chat.MainChatFragment
import com.mayburger.gojekuiclone.ui.main.fragments.home.MainHomeFragment
import com.mayburger.gojekuiclone.ui.main.fragments.promos.MainPromosFragment
import com.mayburger.gojekuiclone.ui.main.fragments.services.MainServicesFragment
import com.mayburger.gojekuiclone.util.rx.RxBus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_tab_layout.view.*


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        goride.setOnClickListener {
            startActivity(Intent(this, FoodActivity::class.java))
        }
        gocar.setOnClickListener {
            startActivity(Intent(this, FoodActivity::class.java))
        }
        gofood.setOnClickListener {
            startActivity(Intent(this, FoodActivity::class.java))
        }
        goshop.setOnClickListener {
            startActivity(Intent(this, FoodActivity::class.java))
        }

        val adapter = TabPagerAdapter(
                this,
                arrayListOf(
                        MainPromosFragment(),
                        MainHomeFragment(),
                        MainChatFragment(),
                )
        )
        pager.adapter = adapter
        TabLayoutMediator(tab, pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.customView = getTabLayout("Promos", R.drawable.promos)
                }
                1 -> {
                    tab.customView = getTabLayout("Home", R.drawable.home)
                }
                2 -> {
                    tab.customView = getTabLayout("Chats", R.drawable.chat)
                }
            }
        }.attach()
        pager.setCurrentItem(1, false)

        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.container, MainServicesFragment(), "")
        manager.commit()

        motion.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                RxBus.getDefault().send(TransitionEvent(p3))
                container.alpha = p3 * 4
                favorites.alpha = 1 - (p3 * 4)
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                if (p1 == R.id.mainStart) {
                    container.alpha = 0f
                }
            }
        })
    }


    override fun onPause() {
        super.onPause()
        motion.transitionToStart()
    }

    fun getTabLayout(title: String, icon: Int): View {
        val tab = LayoutInflater.from(this).inflate(R.layout.main_tab_layout, null, false)
        tab.title.text = title
        tab.icon.setImageResource(icon)
        return tab
    }
}