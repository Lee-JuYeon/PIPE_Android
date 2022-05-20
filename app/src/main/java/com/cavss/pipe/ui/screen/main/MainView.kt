package com.cavss.pipe.ui.screen.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Constraints
import androidx.navigation.NavController
import com.cavss.pipe.model.dock.DockModel
import com.cavss.pipe.ui.custom.adview.google.GoogleAdType
import com.cavss.pipe.ui.custom.adview.google.GoogleAdView
import com.cavss.pipe.ui.custom.dock.DockView
import com.cavss.pipe.ui.custom.framelayout.FrameLayout
import com.cavss.pipe.ui.custom.sheet.CustomSheet
import com.cavss.pipe.ui.custom.sheet.CustomSheetType
import com.cavss.pipe.ui.custom.sheet.CustomSheetVM
import com.cavss.pipe.ui.custom.sheet.inner.APIdetailView
import com.cavss.pipe.ui.screen.main.house.HouseView
import com.cavss.pipe.ui.screen.main.money.MoneyView
import com.cavss.pipe.ui.screen.main.recruit.RecruitView
import com.cavss.pipe.ui.screen.main.setting.SettingView
import com.cavss.pipe.ui.screen.main.startup.StartUpView
import com.cavss.pipe.ui.screen.splash.SplashRequestView
import com.cavss.pipe.vm.APIVM
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainView(
    navController: NavController
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ){
        val maxWidth = this.maxWidth
        val maxHeight = this.maxHeight
        val heightBlock = (maxHeight / 20)

        val pagerState = rememberPagerState(
            pageCount = DockModel.DOCK_LIST.dockList.size,
            initialOffscreenLimit = DockModel.DOCK_LIST.dockList.size,
            infiniteLoop = false,
            initialPage = 0,
        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            FrameLayout(
                setModifier = Modifier
                    .size(
                        width = maxWidth,
                        height = heightBlock * 16
                    )
                    .background(Color.Blue),
                setPageState = pagerState,
                setInnerView = { index : Int ->
                    when(index){ // == pageState.currentPage
                        0 -> {
                            // 지원금 뷰
                            MoneyView()
                        }
                        1 -> {
                            HouseView() // 부동산 뷰
                        }
                        2 -> {
                            StartUpView() // 스타트업 뷰
                        }
                        3 -> {
                            RecruitView() // 취업 뷰
                        }
                        4 -> {
                            SettingView() // 설정 뷰
                        }
                    }
                }
            )

            DockView(
                setList = DockModel.DOCK_LIST.dockList,
                setSize = mapOf(
                    "width" to maxWidth,
                    "height" to heightBlock * 2
                ),
                setState = pagerState
            )

            GoogleAdView(
                setSize = mapOf(
                    "width" to maxWidth,
                    "height" to heightBlock * 2
                ),
                setType = GoogleAdType.BANNER
            )
        }

        CustomSheet(
            onDismiss = {
                Log.e("mException", "MainView, CustomSheet, onDismiss // 사라짐~")
//                        LocalLifecycleOwner.current.lifecycleScope.launch(Dispatchers.IO){
//                            val encryptedCountingNumber = AESHelper.keystoreEncrypt("1".toByteArray(Charsets.UTF_8))
//                            localDB.settingDAO().updateOpenCount(encryptedCountingNumber)
//                        }
            },
            INNER_VIEW = { constraints: Constraints ->
                when(CustomSheetVM.innerViewType.value){
                    CustomSheetType.API_DETAIL -> {
                        APIdetailView(model = APIVM.currentData.value!!)
                    }
                    CustomSheetType.PERMISSION_REQUEST -> {
                        SplashRequestView()
                    }
                    else -> {
                        Log.e("mException", "MainView, CustomSheetView, InnerView // 다른 데이터 value 확인됌")
                    }
                }
            }
        )
    }
}