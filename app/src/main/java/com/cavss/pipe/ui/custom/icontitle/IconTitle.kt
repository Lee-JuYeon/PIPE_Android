package com.cavss.pipe.ui.custom.icontitle

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IconTitle(
    setImage : Int,
    setSize : Int,
    setTitle : String,
    setTitleColour : Color,
    setTitleSize : Int,
    setModifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = setModifier
            .padding(3.dp)
    ) {
        Image(
            painter = painterResource(id = setImage),
            contentDescription = "icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(setSize.dp)
        )
        Text(
            text = setTitle,
            color = setTitleColour,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = setTitleSize.sp,
            modifier = Modifier
                .padding(start = 5.dp)
        )
    }
}