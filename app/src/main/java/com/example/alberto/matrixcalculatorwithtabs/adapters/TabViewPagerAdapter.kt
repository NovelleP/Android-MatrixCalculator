package com.example.alberto.matrixcalculatorwithtabs.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentManagerNonConfig
import android.support.v4.app.FragmentPagerAdapter

class TabViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var fragments = ArrayList<Fragment>()
    private var titles = ArrayList<String>()

    override fun getItem(p0: Int): Fragment {
        return fragments[p0]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    fun addFragment(fragment: Fragment, tittle: String) {
        fragments.add(fragment)
        titles.add(tittle)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}