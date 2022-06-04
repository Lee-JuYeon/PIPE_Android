package com.cavss.pipe.ui.custom.sheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.cavss.pipe.util.colour.Colours
import com.cavss.pipe.util.colour.ColoursPurposeType
import com.cavss.pipe.vm.CustomSheetVM


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CustomSheet(
    INNER_VIEW : @Composable (Constraints) -> Unit,
    onDismiss : @Composable () -> Unit,
    setCustomSheetVM: CustomSheetVM
) {
    val scrollState = rememberScrollState() // scroll state

    AnimatedVisibility(
        visible = setCustomSheetVM.isShow.observeAsState().value ?: false,
        enter = slideInVertically(
            // 오프셋이 -(전체높이)에서 0으로 슬라이딩 하며 내려간다.
            initialOffsetY = { fullHeight: Int -> fullHeight },
            animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
        ),
        exit = slideOutVertically(
            // 오프셋이 0에서 -(전체높이)로 슬라이딩 하여 올라간다.
            targetOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
        )
    ) {
        BoxWithConstraints(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color(0x80000000)
                )
                .clickable {
                    setCustomSheetVM.setSheetVisible(false)
                }
        ) {
            val maxHeight = this.maxHeight
            val modifier = Modifier

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(
                        top = 0.dp,
                        start = 5.dp,
                        end = 5.dp,
                        bottom = 0.dp
                    )
                    .fillMaxWidth()
                    .heightIn(0.dp, (maxHeight / 10) * 7)
                    .clip(
                        RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp
                        )
                    )
                    .background(
                        Colours.redPallet[ColoursPurposeType.LIGHTNESS]!!
                    )
                    .border(
                        1.dp,
                        Color.DarkGray,
                        RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp
                        )
                    )
                    .padding(
                        top = 0.dp,
                        start = 15.dp,
                        end = 15.dp,
                        bottom = 0.dp
                    )
                    .verticalScroll(scrollState)
                    .clickable {

                    }
            ) {
                val boxScope = this@BoxWithConstraints
                INNER_VIEW(boxScope.constraints)
            }
        }
    }

    if (!setCustomSheetVM.isShow.observeAsState().value!!){
        onDismiss()
    }
}

/*
val borderGradient = Brush.linearGradient(
                    colors = listOf(Color.White, Color.LightGray)
                )
                Box(
                    modifier = Modifier
                        .padding(
                            top = 8.dp,
                            bottom = 8.dp,
                            start = 15.dp,
                            end = 15.dp
                        )
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 10.dp,
                                topEnd = 10.dp,
                                bottomEnd = 10.dp,
                                bottomStart = 10.dp
                            )
                        )
                        .background(Color.DarkGray)
                        .border(
                            1.dp,
                            borderGradient,
                            RoundedCornerShape(
                                topStart = 10.dp,
                                topEnd = 10.dp,
                                bottomEnd = 10.dp,
                                bottomStart = 10.dp
                            )
                        )
                        .clickable {
                            isShow.value = false
                        }
                )
 */