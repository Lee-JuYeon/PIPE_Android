package com.cavss.pipe.ui.screen.main.setting.sub

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cavss.pipe.R
import com.cavss.pipe.ui.custom.icontitle.IconTitle
import com.cavss.pipe.ui.custom.sheet.CustomSheetVM
import com.cavss.pipe.vm.APIVM

@Composable
fun SettingBusinessView() {
    val isExpendable = remember{ mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(20.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 200,
                    easing = LinearEasing
                )
            )
    ) {
        IconTitle(
            setImage = R.drawable.icon_business_ask,
            setSize = 30,
            setTitle = stringResource(id = R.string.setting_business),
            setTitleColour = Color.White,
            setTitleSize = 20,
            setModifier = Modifier
                .clickable {
                    isExpendable.value = !isExpendable.value
                }
        )

        if (isExpendable.value){
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.setting_business_email),
                    color = Color.White,
                    maxLines = 1,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.noto_normal)),
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .clickable {

                        }
                )
            }
        }
    }
}