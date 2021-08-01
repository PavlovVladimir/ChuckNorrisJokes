package com.example.android.chucknorrisjokes.ui.webview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.example.android.chucknorrisjokes.R

class ApiWebViewFragment : Fragment() {

    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.fragment_api_webview, container, false)
        webView = layout.findViewById(R.id.webView)
        val webSettings = webView.settings
        webSettings.builtInZoomControls = true

        if (savedInstanceState == null) {
            webView.loadUrl("https://www.icndb.com/api/")
        } else {
            webView.restoreState(savedInstanceState)
        }

        return layout
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }
}