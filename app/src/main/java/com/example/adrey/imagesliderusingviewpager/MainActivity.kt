package com.example.adrey.imagesliderusingviewpager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listImage = arrayOf(
                R.drawable.img1,
                R.drawable.img2,
                R.drawable.img3,
                R.drawable.img4
        )
        val listTitle = resources.getStringArray(R.array.title)
        val mainAdapter = MainAdapter(supportFragmentManager, this, listImage, listTitle)
        mainAdapter.showIndicator(ll_main, vp_main)
        vp_main.adapter = mainAdapter
    }
}
