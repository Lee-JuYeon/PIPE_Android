package com.cavss.pipe.ui.custom.adview.google

sealed class GoogleAdType(val type : String) {
    object BANNER_ADAPTIVE : GoogleAdType("BANNER_ADAPTIVE")
    object BANNER_INLINE_ADAPTIVE : GoogleAdType("BANNER_INLINE_ADAPTIVE")
    object BANNER_TRADITIONAL : GoogleAdType("BANNER_TRADITIONAL")
    object SMART_BANNER_REQUEST : GoogleAdType("SMART_BANNER_REQUEST")
}