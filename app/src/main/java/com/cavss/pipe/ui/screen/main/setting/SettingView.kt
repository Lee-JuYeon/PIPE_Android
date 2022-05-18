package com.cavss.pipe.ui.screen.main.setting

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cavss.pipe.R
import com.cavss.pipe.ui.custom.sheet.CustomSheetVM
import com.cavss.pipe.ui.screen.main.setting.sub.SettingBusinessView
import com.cavss.pipe.ui.screen.main.setting.sub.SettingInformation
import com.cavss.pipe.ui.screen.main.setting.sub.SettingThemeView
import com.cavss.pipe.ui.screen.main.setting.sub.SettingTitleView

@Composable
fun SettingView(
    customSheetVM : CustomSheetVM
) {
    val optionList = LocalContext.current.resources.getStringArray(R.array.setting_options)

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .border(3.dp, Color.Magenta)
            .padding(10.dp)
    ){
        SettingTitleView() // 타이틀 뷰

        SettingThemeView() // 테마 변경 뷰
        SettingBusinessView() // 비즈니스 문의 뷰
        SettingInformation() //  소개 뷰
    }
}