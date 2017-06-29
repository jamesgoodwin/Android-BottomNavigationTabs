package com.hsbc.uitestapp

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_one.*

class FragmentOne : Fragment() {

    private var count: Int = 0
    private val countKey = "Count"

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null && savedInstanceState.containsKey(countKey)) {
            this.count = savedInstanceState.getInt(countKey)
            showCount()
        }

        countButton.setOnClickListener({
            count++
            showCount()
        })
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putInt(countKey, count)
    }

    private fun showCount() {
        countText.text = getString(R.string.countText, count)
    }
}