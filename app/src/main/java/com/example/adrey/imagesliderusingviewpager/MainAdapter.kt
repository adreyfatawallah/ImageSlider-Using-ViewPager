package com.example.adrey.imagesliderusingviewpager

import android.content.Context
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.LinearLayout

/**
 * Created by Muh Adrey Fatawallah on 2/7/2018.
 */
class MainAdapter(fragmentManager: FragmentManager,
                  private val context: Context,
                  private val listImage: Array<Int>,
                  private val listTitle: Array<String>) : FragmentStatePagerAdapter(fragmentManager), ViewPager.OnPageChangeListener {

    override fun getItem(position: Int): Fragment = MainFragment.newInstance(listImage[position], listTitle[position])

    override fun getCount(): Int = listImage.size

    private var indexPage = 0

    private lateinit var linearLayout: LinearLayout
    private lateinit var viewPager: ViewPager

    fun showIndicator(linearLayout: LinearLayout, viewPager: ViewPager) {
        this.linearLayout = linearLayout
        this.viewPager = viewPager

        val res = context.resources
        for (i in 0 until listImage.size) {
            val view = View(context)
            val dimen: Int = res.displayMetrics.density.times(8).toInt()
            val layoutParams = LinearLayout.LayoutParams(dimen, dimen)
            layoutParams.leftMargin = res.displayMetrics.density.times(3).toInt()
            layoutParams.rightMargin = res.displayMetrics.density.times(3).toInt()
            view.layoutParams = layoutParams
            view.setBackgroundResource(R.drawable.bg_indicator)
            view.isSelected = i == 0
            this.linearLayout.addView(view)
        }
        viewPager.addOnPageChangeListener(this)
        setSelectedIndicator()

        autoSlideViewPager()
    }

    private fun autoSlideViewPager() {
        val handler = Handler()
        var arrow = 0
        val runnable = object : Runnable {
            override fun run() {
                handler.postDelayed(this, 3000)

                /**
                 * loop left to right if last index to first index
                 */
                if (indexPage < listImage.size)
                    viewPager.setCurrentItem(indexPage++, true)
                else
                    viewPager.setCurrentItem(0, true)

                /**
                 * loop left to right if last index right to left
                 */
//                if (indexPage <= listImage.size) {
//
//                    if (arrow == 0)
//                        arrow = 1
//
//                    if (arrow == 1) {
//                        viewPager.setCurrentItem(indexPage, true)
//                        indexPage++
//                    }
//
//                    if (indexPage == listImage.size) {
//                        arrow = 2
//                        indexPage--
//                    }
//
//                    if (arrow == 2) {
//                        viewPager.setCurrentItem(indexPage, true)
//                        indexPage--
//
//                        if (indexPage < 0)
//                            arrow = 1
//                    }
//                }
            }
        }
        handler.post(runnable)
    }

    private fun setSelectedIndicator() {
        for (i in 0 until listImage.size) {
            val view = linearLayout.getChildAt(i)
            view.isSelected = i == indexPage
        }
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        indexPage = position
        setSelectedIndicator()
    }
}