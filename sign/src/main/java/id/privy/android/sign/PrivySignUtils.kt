package id.privy.android.sign

import java.net.URLEncoder

object PrivySignUtils {
    /**
     * Default Params Requester
     */
    fun paramsRequest(
        privyID: String = "",
        visibility: Boolean = true,
        fixed: Boolean = false,
        page: Int = 0,
        coordinateX: Float = 0f,
        coordinateY: Float = 0f,
        debugMode: Boolean = false
    ) : String {
        val params: MutableMap<String, Any> = mutableMapOf()
        /**
         * Default parameter
         */
        params["visibility"] = visibility
        params["fixed"] = fixed
        params["debug"] = debugMode

        /**
         * Optional parameter
         */
        if (privyID.isNotEmpty()) params["privyId"] = privyID
        if (page != 0) params["page"] = page
        if (!coordinateX.equals(0f)) params["x"] = coordinateX
        if (!coordinateY.equals(0f)) params["y"] = coordinateY

        return params.map {
            "${URLEncoder.encode(it.key, "UTF-8")}=${URLEncoder.encode(it.value.toString(), "UTF-8")}"
        }.joinToString("&")
    }


    /**
     * Callback Implementor
     * got After action mode, after sign, and after review
     */
    interface PrivySignCallback{
        fun afterAction()
        fun afterSign()
        fun afterReview()
    }
}