package com.cavss.pipe.ui.custom.framelayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraintsScope
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
    setModifier : Modifier,
    setPageState : PagerState,
    setInnerView : @Composable (Int) -> Unit
){

    HorizontalPager(
        state = setPageState,
        modifier = setModifier
            .background(
                Color.Transparent
            ),
        dragEnabled = false // 스크롤 방지
    ) { index ->
        setInnerView(index)
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