package com.cavss.pipe.ui.screen.main.house

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cavss.pipe.R
import com.cavss.pipe.ui.custom.sheet.CustomSheetVM
import com.cavss.pipe.ui.screen.main.apilist.APIListType
import com.cavss.pipe.ui.screen.main.apilist.APIListView
import com.cavss.pipe.vm.APIVM
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HouseView() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .border(3.dp, Color.Magenta)
    ) {
        // 청약, 분양권
        // 체크리스트
        // 청년대상 주택
        val tabData = LocalContext.current.resources.getStringArray(R.array.tab_title_house)
        val pagerState = rememberPagerState(
            pageCount = tabData.size,
            initialOffscreenLimit = tabData.size,
            infiniteLoop = false,
            initialPage = 0,
        )
        val tabIndex = pagerState.currentPage
        val coroutineScope = rememberCoroutineScope()

        TabRow(
            selectedTabIndex = tabIndex,
        ) {
            tabData.forEachIndexed { index, text ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = text
                        )
                    }
                )
            }
        }

        // PAGER
        HorizontalPager(
            state = pagerState,
            dragEnabled = true,
        ) { index : Int ->
            when(index){
                0 -> { // 청약, 분양권
                    APIListView(APIListType.HOUSE_PREMIUM)
                }
                1 -> { // 나에게 맞는 최적 조건의 집 찾기
                    APIListView(APIListType.HOUSE_MYHOME)
                }
                2 -> { // 체크리스트
                    APIListView(APIListType.HOUSE_CHECKLIST)
                }
            }
        }
    }
}