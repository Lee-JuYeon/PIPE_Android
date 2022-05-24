package com.cavss.pipe.ui.custom.apilist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.cavss.pipe.ui.custom.sheet.CustomSheetType
import com.cavss.pipe.ui.custom.sheet.CustomSheetVM

@Composable
fun APIitemView(
    data : ApiDetailItemDTO,
    onClick : (ApiDetailItemDTO) -> Unit
) {
    Box {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.LightGray)
                .border(
                    1.dp,
                    Color.White,
                    RoundedCornerShape(10.dp)
                )
                .padding(10.dp)
                .clickable {
                    //TODO bottomSheet로 자세히보기 구현.
                    CustomSheetVM.setShowBottomSheetState(true)
                    CustomSheetVM.setInnerViewType(CustomSheetType.API_DETAIL)
                    onClick(data)
                }
        ) {
            Text(
                text = data.eventTitle,
                color = Color.Black,
                maxLines = 1,
                fontSize = 23.sp,
                fontFamily = FontFamily(Font(R.font.noto_bold)),
                overflow = TextOverflow.Ellipsis
            )

            TitleWithContents(
                title = stringResource(id = R.string.apiDetailView_eventApplyPeriod),
                contentList = listOf(
                    data.eventApplyPeriodStart,
                    "~",
                    data.eventApplyPeriodEnd
                )
            )

            TitleWithContents(
                title = "지원내용",
                contentList = listOf(
                    "머시기 머시기 지원내용~",
                    " - 머시기 머시기 지원내용~",
                    " - 머시기 머시기 지원내용~",
                    " - 머시기 머시기 지원내용~",
                    " - 머시기 머시기 지원내용~",
                    data.companySupportContent
                )
            )
        }

        Image(
            painter = painterResource(id = R.drawable.image_bookmark),
            contentDescription = "북마튼 버튼",
            modifier = Modifier
                .padding(10.dp)
                .size(25.dp, 25.dp)
                .align(Alignment.TopEnd)
                .clickable {
                    //TODO 북마크 기능
                }
        )
    }
}

/*
Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
        ) {
            Text(text = "대상 bold title")
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {
                Text(text = "성별") // 남자
                Text(text = "지역")  // 서울시 종로구
                Text(text = "취업상태")  // false
                Text(text = "창업기간")  // 0
                Text(text = "전공분야") // 이과
                Text(text = "직업") // 학생
                Text(text = "연령대상") // 청소년
            }
        }
 */