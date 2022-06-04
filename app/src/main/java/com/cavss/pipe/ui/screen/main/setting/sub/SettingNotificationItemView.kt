package com.cavss.pipe.ui.screen.main.setting.sub

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cavss.pipe.R
import com.cavss.pipe.model.setting.NotificationDTO
import com.cavss.pipe.ui.custom.icontitle.IconTitle
import com.cavss.pipe.ui.custom.sheet.CustomSheetType


@Composable
fun SettingNotificationItemView(
    model : NotificationDTO
){
    val isExpendable = remember{ mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(
                start = 10.dp,
                top = 5.dp,
                bottom = 5.dp,
                end = 5.dp
            )
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 200,
                    easing = LinearEasing
                )
            )
    ) {
        Text(
            text = model.notifyTitle,
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isExpendable.value = !isExpendable.value
                }
        )

        Text(
            text = model.date,
            maxLines = 1,
            fontSize = 13.sp,
            textAlign = TextAlign.Left,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isExpendable.value = !isExpendable.value
                }
        )

        if (isExpendable.value){
            Text(
                text = model.notifyContent,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
                        isExpendable.value = !isExpendable.value
                    }
            )
        }
    }


//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween,
//        modifier = Modifier
//
//    ) {
//        Text(
//            text = model.notifyTitle,
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            maxLines = 1,
//            color = Color.White,
//            overflow = TextOverflow.Ellipsis,
//            modifier = Modifier
//                .padding(horizontal = 10.dp)
//                .fillMaxWidth()
//                .widthIn(100.dp, 200.dp)
//                .clickable {
//                    onClick()
//                }
//        )
//
//        Image(
//            painter = painterResource(id = R.drawable.icon_zoom),
//            contentDescription = "자세히 보기",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .padding(5.dp)
//                .size(30.dp)
//                .clickable {
//                    onClick()
//                }
//        )
//    }
}