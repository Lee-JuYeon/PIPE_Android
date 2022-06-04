package com.cavss.pipe.ui.screen.main.setting

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cavss.pipe.R
import com.cavss.pipe.vm.CustomSheetVM
import com.cavss.pipe.ui.screen.main.setting.sub.*

@Composable
fun SettingView(
    setCustomSheetVM : CustomSheetVM
) {
    val optionList = LocalContext.current.resources.getStringArray(R.array.setting_options)
    val scrollState = rememberScrollState() // scroll state

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(scrollState)
    ){
        SettingTitleView() // 타이틀 뷰

//        SettingThemeView() // 테마 변경 뷰
        SettingNotificationView(
            setCustomSheetVM = setCustomSheetVM
        ) // 공지사항 뷰
        SettingInformation() //  소개 뷰
        SettingBusinessView() // 비즈니스 문의 뷰
        SettingIncomeView() // 수익공개 뷰
    }
}

/*
TODO : 색상변경뷰 리액트 완성해야함. vm에서 라이브 데이터 가져올때 value가 자꾸 null값임.
 */