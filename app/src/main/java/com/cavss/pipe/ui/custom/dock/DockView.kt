package com.cavss.pipe.ui.custom.dock

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cavss.pipe.model.dock.DockModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun DockView(
    setList : List<DockModel>,
    setSize : Map<String, Dp>,
    setState : PagerState
){

    // 메모리 관리가 들어간 lazyColumn
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 0.dp),
        modifier = Modifier
            .padding(top = 2.dp)
            .size(
                width = (setSize["width"] ?: 0.dp) - 10.dp,
                height = (setSize["height"] ?: 0.dp) - 2.dp
            )
            .clip(
                RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
            )
            .background(Color(0x85ffffff))
    ){
        items(setList){ model ->
            DockItemView(
                setModel = model,
                setState = setState,
                setColour = Color.Black,
                setSize = setSize
            )
        }
    }
}