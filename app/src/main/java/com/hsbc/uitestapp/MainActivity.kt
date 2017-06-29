package com.hsbc.uitestapp

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var fragmentOne: FragmentOne = FragmentOne()
    private var fragmentTwo: FragmentTwo = FragmentTwo()
    private var fragmentThree: FragmentThree = FragmentThree()

    private var fragmentSavedState: HashMap<Class<Fragment>, Fragment.SavedState?> = HashMap()
    private val tag = "TabContentFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener {
            item ->
            when (item.itemId) {
                R.id.one -> showTabContent(fragmentOne)
                R.id.two -> showTabContent(fragmentTwo)
                R.id.three -> showTabContent(fragmentThree)

            }
            true
        }
        showTabContent(FragmentOne())
    }

    private fun showTabContent(fragment: Fragment) {
        // save all current state
        val currentFragment = fragmentManager.findFragmentByTag(tag)
        if (currentFragment!= null) {
            fragmentSavedState.put(currentFragment.javaClass, fragmentManager.saveFragmentInstanceState(currentFragment))
        }

        if (!fragment.isVisible) {
            fragment.setInitialSavedState(fragmentSavedState[fragment.javaClass])

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content, fragment, tag)
                    .commit()
        }
    }
}