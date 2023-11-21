package com.saralpe.app.view.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.saralpe.app.R
import com.saralpe.app.databinding.ActivityWebViewBinding

class WebViewActivity : BaseActivity() {

    private lateinit var binding:ActivityWebViewBinding

    private lateinit var context: Context

    private var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        setUpToolbar()

        if(intent != null && intent.hasExtra("URL")){
            url = intent.getStringExtra("URL") as String
        }

// Enable JavaScript (if needed)
        val webSettings: WebSettings = binding.webview.settings
        webSettings.javaScriptEnabled = true

        // Set a WebViewClient to handle page navigation and load inside the WebView
        binding.webview.webViewClient = WebViewClient()

        // Set a WebChromeClient to handle progress and other WebView-related events (optional)
        binding.webview.webChromeClient = WebChromeClient()

        // Load a website URL
        binding.webview.loadUrl(url)
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = "Buy Insurance"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(android.R.id.home == item.itemId){
            onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (binding.webview.canGoBack()) {
            binding.webview.goBack()
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}