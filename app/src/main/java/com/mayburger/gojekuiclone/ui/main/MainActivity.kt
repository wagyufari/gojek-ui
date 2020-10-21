package com.mayburger.gojekuiclone.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.mayburger.gojekuiclone.BR
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.databinding.ActivityMainBinding
import com.mayburger.gojekuiclone.ui.adapters.TabPagerAdapter
import com.mayburger.gojekuiclone.ui.base.BaseActivity
import com.mayburger.gojekuiclone.ui.main.fragments.chat.MainChatFragment
import com.mayburger.gojekuiclone.ui.main.fragments.home.MainHomeFragment
import com.mayburger.gojekuiclone.ui.main.fragments.promos.MainPromosFragment
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
                    tab.customView = getTabLayout("Promos",R.drawable.promos)
                }
                1 -> {
                    tab.customView = getTabLayout("Home",R.drawable.home)
                }
                2 -> {
                    tab.customView = getTabLayout("Chat",R.drawable.chat)
                }
            }
        }.attach()
        pager.setCurrentItem(1,false)
    }


    fun getTabLayout(title:String,icon:Int): View {
        val tab = LayoutInflater.from(this).inflate(R.layout.main_tab_layout,null,false)
        tab.title.text = title
        tab.icon.setImageResource(icon)
        return tab
    }
}