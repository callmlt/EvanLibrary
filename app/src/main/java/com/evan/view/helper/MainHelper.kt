package com.evan.view.helper

import android.content.Context
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.evan.R
import com.evan.fragment.IndexFragment
import com.evan.fragment.MeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 *
 * 主页面帮助类
 * @author GR 2022-01-17 19:51:00
 */
class MainHelper(private val context: Context) {

    private var fragments: Array<Fragment>? = null
    private var lastFragment: Int = 0
    private var fragmentManager: FragmentManager? = null
    private var bottomNavigationView: BottomNavigationView? = null


    private val changeFragment = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.index -> {
                if (lastFragment != 0) {
                    switchFragment(lastFragment, 0)
                    lastFragment = 0
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.me -> {
                if (lastFragment != 1) {
                    switchFragment(lastFragment, 1)
                    lastFragment = 1
                }
                return@OnNavigationItemSelectedListener true
            }
            else -> {
            }
        }
        false
    }

    fun initBottomNavigationView(bottomNavigationView: BottomNavigationView, fragmentManager: FragmentManager) {
        this.bottomNavigationView = bottomNavigationView
        this.fragmentManager = fragmentManager
        val indexFragment = IndexFragment()
        val meFragment = MeFragment()

        fragments = arrayOf(indexFragment, meFragment)
        lastFragment = 0
        fragmentManager.beginTransaction().replace(R.id.tabContentLay, indexFragment).show(indexFragment).commit()
        bottomNavigationView.itemIconTintList = null
        bottomNavigationView.setOnNavigationItemSelectedListener(changeFragment)
    }

    private fun switchFragment(lastFragment: Int, index: Int) {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.hide(fragments!![lastFragment])
        if (!fragments!![index].isAdded) {
            transaction.add(R.id.tabContentLay, fragments!![index])
        }
        transaction.show(fragments!![index]).commitAllowingStateLoss()
    }
}
