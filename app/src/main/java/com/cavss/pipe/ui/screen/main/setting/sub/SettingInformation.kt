package com.cavss.pipe.ui.screen.main.setting.sub

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cavss.pipe.R
import com.cavss.pipe.ui.custom.icontitle.IconTitle

@Composable
fun SettingInformation() {

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
            setImage = R.drawable.ic_launcher_background,
            setSize = 30,
            setTitle = stringResource(id = R.string.setting_information),
            setTitleColour = Color.White,
            setTitleSize = 20,
            setModifier = Modifier
                .clickable {
                    isExpendable.value = !isExpendable.value
                    //TODO :
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
                    text = stringResource(id = R.string.setting_information_datause),
                    color = Color.White,
                    maxLines = 1,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .clickable {
                            isExpendable.value = !isExpendable.value
                            //TODO : 웹뷰로 이동
                        }
                )
                Text(
                    text = stringResource(id = R.string.setting_information_terms_of_service),
                    color = Color.White,
                    maxLines = 1,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .clickable {
                            isExpendable.value = !isExpendable.value
                            //TODO : 웹뷰로 이동
                        }
                )
                Text(
                    text = stringResource(id = R.string.setting_information_opensource),
                    color = Color.White,
                    maxLines = 1,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .clickable {
                            isExpendable.value = !isExpendable.value
                            //TODO : 웹뷰로 이동
                        }
                )
                Text(
                    text = stringResource(id = R.string.setting_information_donate),
                    color = Color.White,
                    maxLines = 1,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .clickable {
                            isExpendable.value = !isExpendable.value
                            //TODO : 웹뷰로 이동
                        }
                )
            }
        }
    }
}