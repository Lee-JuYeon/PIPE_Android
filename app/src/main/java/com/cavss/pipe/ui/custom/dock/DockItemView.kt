package com.cavss.pipe.ui.custom.dock

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cavss.pipe.model.dock.DockModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun DockItemView(
    setModel : DockModel,
    setState : PagerState,
    setColour : Color,
    setSize : Map<String, Dp>
){

    val scope = rememberCoroutineScope()
    Box(
        modifier= Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
            .background(
                if (setModel.itemID == DockModel.DOCK_LIST.dockList[setState.currentPage].itemID) {
                    setColour.copy(alpha = 0.2f)
                } else {
                    Color.Transparent
                }
            )
            .clickable {
                when (setModel.itemID) {
                    DockModel.Community.itemID -> {
                        scope.launch {
                            setState.scrollToPage(0)
                        }
                    }
                    DockModel.Money.itemID -> {
                        scope.launch {
                            setState.scrollToPage(0)
                        }
                    }
                    DockModel.House.itemID -> {
                        scope.launch {
                            setState.scrollToPage(1)
                        }
                    }
                    DockModel.StartUp.itemID -> {
                        scope.launch {
                            setState.scrollToPage(2)
                        }
                    }
                    DockModel.Recruit.itemID -> {
                        scope.launch {
                            setState.scrollToPage(3)
                        }
                    }
                    DockModel.Setting.itemID -> {
                        scope.launch {
                            setState.scrollToPage(4)
                        }
                    }
                }
            }
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 200,
                    easing = LinearEasing
                )
            )
            .size(
                width = (setSize["height"] ?: 0.dp) - 5.dp,
                height = (setSize["height"] ?: 0.dp) - 5.dp
            )
            .padding(5.dp)
    ) {
        Image(
            painter = painterResource(id = setModel.itemImage),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
        )
    }
}