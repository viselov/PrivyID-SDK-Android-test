package id.privy.android.sign

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.webkit.*

@SuppressLint("AddJavascriptInterface")
class PrivySignViewer : WebView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    private lateinit var callback: PrivySignUtils.PrivySignCallback

    /**
     * Google Chrome Client
     * to got best mode on your web browser client
     */
    private val chromeClient = object : WebChromeClient() {
        override fun onJsPrompt(
            view: WebView?,
            url: String?,
            message: String?,
            defaultValue: String?,
            result: JsPromptResult?
        ): Boolean {
            Log.d("info-web", "message: $message with default value $defaultValue, and result $result")
            return super.onJsPrompt(view, url, message, defaultValue, result)
        }
    }

    init {
        settings.apply {
            javaScriptEnabled = true
            useWideViewPort = false
            loadWithOverviewMode = true
            domStorageEnabled = true
            webChromeClient = chromeClient
        }
        addJavascriptInterface(WebAppInterface(), "Android")
    }


    /**
     * set callback from action review, signed, and after action
     */
    fun setCallbackSign(callback: PrivySignUtils.PrivySignCallback) {
        this.callback = callback
    }

    /**
     * Open Document Method, you can add custom header for example add Token or others
     */
    fun openDocument(tokenDocument: String, queryDocument: String, headersDocument: Map<String, String> = emptyMap()) {
        if (headersDocument.isEmpty()) {
            /**
             * Without Header Configuration
             */
            loadUrl(
                BuildConfig.SERVER_URL
                    .plus(tokenDocument)
                    .plus("?")
                    .plus(queryDocument)
            )
        } else {
            /**
             * With header Configuration
             */
            loadUrl(
                BuildConfig.SERVER_URL
                    .plus(tokenDocument)
                    .plus("?")
                    .plus(queryDocument)
                ,
                headersDocument.toMutableMap()
            )
        }
    }


    /**
     * PrivySDK Javascript Interface
     * handle and get state from Action
     *
     */
    inner class WebAppInterface{
        @JavascriptInterface
        fun afterSign() {
            Log.d("interface" , "after-sign")
            callback.afterSign()
        }

        @JavascriptInterface
        fun afterReview() {
            Log.d("interface" , "after-review")
            callback.afterReview()
        }

        @JavascriptInterface
        fun afterAction() {
            Log.d("interface" , "after-action")
            callback.afterAction()
        }
    }
}