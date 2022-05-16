package com.cavss.pipe.ui.screen.main.apilist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cavss.pipe.model.item.ApiDetailItemDTO
import com.cavss.pipe.ui.custom.sheet.CustomSheetVM
import com.cavss.pipe.vm.APIVM

@Composable
fun APIListView(
    customSheetVM : CustomSheetVM
) {
//    Text(text = "텍스트 : ${APIVM.getData(APIListType.MONEY_GOVERNMENT).value!!}")
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .background(Color.Transparent)
    ){
        items(APIVM.getData(APIListType.MONEY_GOVERNMENT).value!!){ item : ApiDetailItemDTO ->
            APIitemView(
                customSheetVM = customSheetVM,
                data = item,
                onClick = {
//                    apiVM.setCurrentData(it)
                }
            )
        }
    }
}