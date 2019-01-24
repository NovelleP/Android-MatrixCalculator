package com.example.alberto.matrixcalculatorwithtabs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.example.alberto.matrixcalculatorwithtabs.adapters.TabViewPagerAdapter
import com.example.alberto.matrixcalculatorwithtabs.tabs_fragments.MatrixATab
import com.example.alberto.matrixcalculatorwithtabs.tabs_fragments.MatrixBTab
import com.example.alberto.matrixcalculatorwithtabs.tabs_fragments.MatrixResultTab
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Configuring Tabs*/
        var tabLayout: TabLayout = mainActivityTL
        var viewPager: ViewPager = mainActivityVP
        viewPager.offscreenPageLimit = 3 //to remember the state of the 3 tabs
        tabLayout.setupWithViewPager(viewPager)
        setUpViewPager(viewPager)
    }

    private fun setUpViewPager(viewPager: ViewPager) {
        var tabViewPagerAdapter = TabViewPagerAdapter(supportFragmentManager)
        tabViewPagerAdapter.addFragment(MatrixATab(), "MatrixA")
        tabViewPagerAdapter.addFragment(MatrixBTab(), "MatrixB")
        tabViewPagerAdapter.addFragment(MatrixResultTab(), "MatrixResult")
        viewPager.adapter = tabViewPagerAdapter
    }
}