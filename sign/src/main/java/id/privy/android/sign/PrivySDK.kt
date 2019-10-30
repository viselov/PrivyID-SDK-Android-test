package id.privy.android.sign

import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent

class PrivySDK(private val context: Context) {
    fun openDocument(tokenDocument: String, titleDocument: Boolean = true, queryDocument: String) {
        val builder = CustomTabsIntent.Builder()
        builder.apply {
            setInstantAppsEnabled(false)
            setStartAnimations(context, R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_top)
            setExitAnimations(context, R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom)
            setShowTitle(titleDocument)
        }
        val intent = builder.build()
        intent.launchUrl(context,
            Uri.parse(
                BuildConfig.SERVER_URL
                    .plus(tokenDocument)
                    .plus("?")
                    .plus(queryDocument)
            )
        )
    }
}