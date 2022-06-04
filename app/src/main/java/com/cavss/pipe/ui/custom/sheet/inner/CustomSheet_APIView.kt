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
fun CustomSheet_APIView(model : ApiDetailItemDTO) {
    Text(
        text = model.eventTitle,
        color = Color.Black,
        maxLines = 1,
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.noto_bold)),
        overflow = TextOverflow.Visible
    )// 이벤트 명

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_companySupportField),
        contentList = listOf(
            model.companySupportField
        )
    )// 기관 지원분야

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_companySupportContent),
        contentList = listOf(
            model.companySupportContent
        )
    )// 기관 지원내용

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_eventApplyPeriod),
        contentList = listOf(
            model.eventApplyPeriodStart,
            "~",
            model.eventApplyPeriodEnd
        )
    )// 신청기간

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_eventApplyHow),
        contentList = listOf(
            model.eventApplyHow
        )
    )//신청방법

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_eventApplyProcess),
        contentList = listOf(
            model.eventApplyProcess
        )
    )//신청절차

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_eventTestWat),
        contentList = listOf(
            model.eventTestWay
        )
    )//평가방법

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_eventSummary),
        contentList = listOf(
            model.eventSummary
        )
    )//개요

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_job),
        contentList = listOf(
            model.job
        )
    )//대상 직업

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_jobState),
        contentList = listOf(
            model.jobState
        )
    )//대상 취업상태

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_workPeriod),
        contentList = listOf(
            model.workPeriod
        )
    )//대상 창업기간

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_major),
        contentList = listOf(
            model.major
        )
    )//대상 전공분야

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_age),
        contentList = listOf(
            model.age
        )
    )//대상 연령대상

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_companyTitle),
        contentList = listOf(
            model.companyTitle
        )
    )//기관 명

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_companyType),
        contentList = listOf(
            model.companyType
        )
    )//기관 종류 (민간 또는 공공)

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_companyDigit),
        contentList = listOf(
            model.companyDepartment,
            model.companyDigit,
            model.companyEmail
        )
    )//기관 연락처

    TitleWithContents(
        title = stringResource(id = R.string.apiDetailView_companyHomePage),
        contentList = listOf(
            model.companyURL
        )
    )//기관 홈페이지

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
    // 북마크된 횟수
}
