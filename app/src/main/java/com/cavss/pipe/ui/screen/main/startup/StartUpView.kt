package com.cavss.pipe.ui.screen.main.startup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cavss.pipe.ui.custom.sheet.CustomSheetVM
import com.cavss.pipe.vm.APIVM

@Composable
fun StartUpView(
    customSheetVM : CustomSheetVM,
    apiVM : APIVM
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        // 스타트업 대회
        // 스타트업 사무실
    }
}