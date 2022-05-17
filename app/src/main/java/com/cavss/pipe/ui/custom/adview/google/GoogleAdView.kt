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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class GoogleAdType(val type : String) {
    object BANNER : GoogleAdType("BANNER")
}

@Composable
fun GoogleAdView(
    setSize : Map<String, Dp>,
    setType : GoogleAdType
) {
    when(setType){
        GoogleAdType.BANNER -> {
            Text(
                text = "Google View",
                modifier = Modifier
                    .size(
                        width = (setSize["width"] ?: 0.dp),
                        height = (setSize["height"] ?: 0.dp)
                    )
                    .background(Color.Blue)
                    .border(1.dp, Color.White),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}