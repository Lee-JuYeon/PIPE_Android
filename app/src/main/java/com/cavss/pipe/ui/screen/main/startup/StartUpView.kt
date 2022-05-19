package com.cavss.pipe.ui.screen.main.startup

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
fun StartUpView() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .border(3.dp, Color.Magenta)
    ) {
        // 스타트업 대회
        // 스타트업 사무실
        val tabData = LocalContext.current.resources.getStringArray(R.array.tab_title_startup)
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
                0 -> { // 대회
                    APIListView(type = APIListType.STARTUP_EVENT)
                }
                1 -> { // 사무실
                    APIListView(type = APIListType.STARTUP_LAB)
                }
            }
        }
    }
}