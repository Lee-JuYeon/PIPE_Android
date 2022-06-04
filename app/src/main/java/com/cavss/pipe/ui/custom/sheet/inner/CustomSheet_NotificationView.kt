package com.cavss.pipe.ui.custom.sheet.inner

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.cavss.pipe.R
import com.cavss.pipe.model.setting.NotificationDTO
import com.cavss.pipe.ui.custom.adview.google.GoogleAdUtil.findActivity
import com.cavss.pipe.ui.custom.sheet.CustomSheetType
import com.cavss.pipe.ui.screen.main.setting.sub.SettingNotificationItemView
import com.cavss.pipe.vm.CustomSheetVM
import com.cavss.pipe.vm.NotificationVM

@Composable
fun CustomSheet_NotificationView() {
    var vm : NotificationVM = ViewModelProvider(LocalContext.current.findActivity() as ViewModelStoreOwner).get(NotificationVM::class.java)
    val list = vm.notificationList.observeAsState().value ?: listOf()

    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .heightIn(0.dp, 300.dp)
    ){
        item {
            Text(
                text = stringResource(id = R.string.sheet_notificaton_title),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(5.dp)
            )
        }

        items(list){ dto : NotificationDTO ->
            SettingNotificationItemView(model = dto)
        }
    }
}