package com.big0.chung.tournote.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView

import com.big0.chung.tournote.R

class AboutHelpFragment : Fragment() {

    private var mUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mUrl = arguments!!.getString(ARG_URL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_help, container, false)

        val mWebView: WebView? = view.findViewById(R.id.webview)
        mWebView?.settings?.javaScriptEnabled = true
        mWebView?.settings?.setSupportZoom(true)
        mWebView?.settings?.builtInZoomControls = true

        if (mUrl != null) {
            mWebView?.loadUrl(mUrl)
        }

        return view
    }

    companion object {
        private val ARG_URL = "url"

        fun newInstance(url: String): AboutHelpFragment {
            val fragment = AboutHelpFragment()
            val args = Bundle()
            args.putString(ARG_URL, url)
            fragment.arguments = args
            return fragment
        }
    }


}
