package com.cavss.pipe.ui.custom.sheet.inner

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cavss.pipe.R
import com.cavss.pipe.model.item.ApiDetailItemDTO
import com.cavss.pipe.ui.custom.apilist.TitleWithContents

@Composable
fun APIdetailView(model : ApiDetailItemDTO) {
    Text(
        text = model.eventTitle,
        color = Color.Black,
        maxLines = 1,
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.noto_bold)),
        overflow = TextOverflow.Visible
    )// 이벤트 명

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_eventApplyPeriod),
        contentList = listOf(
            model.eventApplyPeriodStart,
            "~",
            model.eventApplyPeriodEnd
        )
    )// 신청기


    Image(
        painter = painterResource(id = R.drawable.image_share),
        contentDescription = "공유 버튼",
        modifier = Modifier
            .padding(10.dp)
            .size(25.dp, 25.dp)
//            .align(Alignment.BottomEnd)
            .clickable {
                //TODO 공유
            }
    )
}
