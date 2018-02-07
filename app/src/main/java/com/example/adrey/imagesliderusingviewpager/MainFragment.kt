package com.example.adrey.imagesliderusingviewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Created by Muh Adrey Fatawallah on 2/7/2018.
 */
class MainFragment : Fragment() {

    companion object {
        private const val PARAM_IMAGE = "image"
        private const val PARAM_TITLE = "title"

        fun newInstance(image: Int, title: String) : MainFragment {
            val mainFragment = MainFragment()
            val args = Bundle()
            args.putInt(PARAM_IMAGE, image)
            args.putString(PARAM_TITLE, title)
            mainFragment.arguments = args
            return mainFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        val image = args.getInt(PARAM_IMAGE)
        val title = args.getString(PARAM_TITLE)

        im_main.setImageResource(image)
        tx_main.text = title
    }
}