package com.cavss.pipe.ui.screen.main.money

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.cavss.pipe.model.item.ApiDetailItemDTO
import com.cavss.pipe.ui.custom.sheet.CustomSheetVM
import com.cavss.pipe.ui.screen.main.apilist.APIListView
import com.cavss.pipe.ui.screen.main.apilist.MoneyItemActivity
import com.cavss.pipe.ui.screen.main.apilist.APIitemView
import com.cavss.pipe.ui.screen.main.apilist.TabMoneyGovernment
import com.cavss.pipe.vm.APIVM
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MoneyView(
    customSheetVM : CustomSheetVM,
    apiVM : APIVM
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        val tabData = LocalContext.current.resources.getStringArray(R.array.tab_title_money)
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
                0 -> { // 정부 지원금
                    APIListView(
                        customSheetVM = customSheetVM
                    )
                }
                1 -> { // 장학금
//                    APIListView(
//                        customSheetVM = customSheetVM,
//                        apiVM = apiVM
//                    )
                }
                2 -> { // 활동 지원금
//                    APIListView(
//                        customSheetVM = customSheetVM,
//                        apiVM = apiVM
//                    )
                }
            }
        }
    }
}