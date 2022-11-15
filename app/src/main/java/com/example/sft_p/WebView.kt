package com.example.sft_p

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class WebView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.searchview)

        val webView = findViewById<android.webkit.WebView>(R.id.webview)
        webView.apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
        }
        webView.loadUrl("https://www.naver.co.kr/")
    }

    override fun onBackPressed() {
        val webView = findViewById<android.webkit.WebView>(R.id.webview)
        if(webView.canGoBack())
        {
            webView.goBack()
        }
        else
        {
            finish()
        }
    }
}
