package com.cavss.pipe.ui.screen.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cavss.pipe.model.dock.DockModel
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
import java.util.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainView(
    navController: NavController
) {

    val customSheetVM : CustomSheetVM = CustomSheetVM()
    customSheetVM.isShow.observe(LocalLifecycleOwner.current){
        Log.e("mException", "리턴값 : ${it}")
    }

    val apiVM = APIVM.getInstacne(LocalContext.current)

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

        FrameLayout(
            setModifier = Modifier
                .size(
                    width = maxWidth,
                    height = heightBlock * 18
                )
                .background(Color.Transparent)
                .align(Alignment.TopCenter),
            setPageState = pagerState,
            setInnerView = { index : Int ->
                when(index){ // == pageState.currentPage
                    0 -> {
                        MoneyView( // 지원금 뷰
                            customSheetVM = customSheetVM,
                            apiVM = apiVM,
                            setModifier = Modifier
                        )
                    }
                    1 -> {
                        HouseView( // 부동산 뷰
                            customSheetVM = customSheetVM,
                            apiVM = apiVM
                        )
                    }
                    2 -> {
                        StartUpView( // 스타트업 뷰
                            customSheetVM = customSheetVM,
                            apiVM = apiVM
                        )
                    }
                    3 -> {
                        RecruitView( // 취업 뷰
                            customSheetVM = customSheetVM,
                            apiVM = apiVM
                        )
                    }
                    4 -> {
                        SettingView( // 설정 뷰
                            customSheetVM = customSheetVM
                        )
                    }
                }
            }
        )

        DockView(
            setList = DockModel.DOCK_LIST.dockList,
            setModifier = Modifier
                .align(Alignment.BottomCenter),
            setSize = mapOf(
                "width" to maxWidth,
                "height" to heightBlock * 2
            ),
            setState = pagerState
        )

        CustomSheet(
            vm = customSheetVM,
            onDismiss = {
                Log.e("mException", "MainView, CustomSheet, onDismiss // 사라짐~")
//                        LocalLifecycleOwner.current.lifecycleScope.launch(Dispatchers.IO){
//                            val encryptedCountingNumber = AESHelper.keystoreEncrypt("1".toByteArray(Charsets.UTF_8))
//                            localDB.settingDAO().updateOpenCount(encryptedCountingNumber)
//                        }
            },
            INNER_VIEW = { constraints: Constraints ->
                when(customSheetVM.innerViewType.value){
                    CustomSheetType.API_DETAIL.type -> {
                        APIdetailView(model = apiVM.currentData.value!!)
                    }
                    CustomSheetType.PERMISSION_REQUEST.type -> {
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