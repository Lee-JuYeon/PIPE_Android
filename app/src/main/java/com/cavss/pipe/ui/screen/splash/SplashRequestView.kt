package com.cavss.pipe.ui.screen.splash

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cavss.pipe.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplashRequestView() {
    val permissionsState = rememberMultiplePermissionsState(permissions = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.INTERNET
    ))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(
            text = stringResource(id = R.string.bottomSheet_permission_requestPermission),
            color = Color.Black,
            maxLines = 1,
            fontSize = 20.sp,
            fontFamily =  FontFamily(Font(R.font.noto_bold))
        )

        Text(
            text = stringResource(id = R.string.bottomSheet_permission_requestNow),
            color = Color.Black,
            maxLines = 1,
            fontSize = 14.sp,
            fontFamily =  FontFamily(Font(R.font.noto_normal)),
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomEnd = 10.dp,
                        bottomStart = 10.dp
                    )
                )
                .background(Color.Cyan)
                .border(
                    2.dp,
                    Color.White,
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomEnd = 10.dp,
                        bottomStart = 10.dp
                    )
                )
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 0.dp,
                    bottom = 0.dp
                )
                .clickable {
                    permissionsState.launchMultiplePermissionRequest()
                }
        )
    }

    val permissionList = listOf(R.string.bottomSheet_permission_gps, R.string.bottomSheet_permission_internet)
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        contentPadding = PaddingValues(horizontal = 0.dp),
        modifier = Modifier
            .fillMaxWidth()
    ){
        items(permissionList){ permission ->
            Text(
                text = stringResource(id = permission),
                color = Color.Black,
                maxLines = 1,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(end = 10.dp)
            )
        }
    }

    Text(
        text = stringResource(id = R.string.bottomSheet_permission_reasonWhy),
        color = Color.Black,
        maxLines = 1,
        fontSize = 20.sp,
        fontFamily =  FontFamily(Font(R.font.noto_bold)),
        modifier = Modifier
            .padding(top = 10.dp)
    )

    Text(
        text = "개인정보와 위치기반 맞춤형 광고, 정보를 제공하며 향후 통계의 자료가 되며 인터넷으로 VPN사용 체크를 합니다.",
        color = Color.Black,
        fontSize = 15.sp,
    )

    Text(
        text = stringResource(id = R.string.bottomSheet_permission_canUseWithoutPermission),
        color = Color.Black,
        maxLines = 1,
        fontSize = 20.sp,
        fontFamily =  FontFamily(Font(R.font.noto_bold)),
        modifier = Modifier
            .padding(top = 10.dp)
    )
}