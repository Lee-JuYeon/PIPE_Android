package com.cavss.pipe.ui.custom.framelayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FrameLayout(
    setSize : Map<String, Dp>,
    pageState : PagerState,
    innerView : @Composable (Int) -> Unit
){
    HorizontalPager(
        state = pageState,
        modifier = Modifier
            .size(
                width = setSize["width"]!!,
                height = setSize["height"]!!
            )
            .background(
                Color.Transparent
            ),
        dragEnabled = false // 스크롤 방지
    ) { index ->
        innerView(index)
    }
}

/*
 val pageList = listOf(HomeView(), StoryView(), ProfileView())
    val pagerState = rememberPagerState(
        pageCount = pageList.size,
        initialOffscreenLimit = 2,
        infiniteLoop = true,
        initialPage = 0,
    )
    HorizontalPager(state = pagerState) { index ->
        HomeView()
        StoryView()
        ProfileView()
    }
 */