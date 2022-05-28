package com.cavss.pipe.ui.custom.adview.google

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.cavss.pipe.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun GoogleAdView(
    setSize : Map<String, Dp>,
    setType : GoogleAdType
) {
    val testBannerID = "ca-app-pub-3940256099942544~3347511713"
    val adWidth = LocalConfiguration.current.screenWidthDp - 32
    val context = LocalContext.current

    when(setType){
        GoogleAdType.BANNER_ADAPTIVE -> {
            // shows an adaptive banner test ad
            AndroidView(
                factory = {
                    AdView(context).apply {
                        adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                            context,
                            adWidth
                        )
                        adUnitId = testBannerID
                        loadAd(AdRequest.Builder().build())
                    }
                }
            )
        }

        GoogleAdType.BANNER_INLINE_ADAPTIVE -> {
            AndroidView(
                factory = {
                    AdView(context).apply {
                        adSize = AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(
                            context,
                            adWidth
                        )
                        adUnitId = testBannerID
                        loadAd(AdRequest.Builder().build())
                    }
                }
            )
        }

        GoogleAdType.BANNER_TRADITIONAL -> {
            AndroidView(
                factory = {
                    AdView(context).apply {
                        adSize = AdSize.BANNER
                        adUnitId = context.getString(R.string.googleAd_test_banner)
                        loadAd(AdRequest.Builder().build())
                    }
                }
            )
        }
    }
}