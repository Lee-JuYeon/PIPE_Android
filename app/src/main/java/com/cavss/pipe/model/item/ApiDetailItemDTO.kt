package com.cavss.pipe.model.item

data class ApiDetailItemDTO(
    val gender : String,                // 대상 성별
    val address : String,               // 대상 지역
    val jobState : Boolean,             // 대상 취업상태
    val workPeriod : String,            // 대상 창업기간
    val major : String,                 // 대상 전공분야
    val job : String,                   // 대상 직업
    val age : String,                   // 대상 연령대상
    val companyTitle : String,          // 기관 명
    val companyType : String,           // 기관 종류
    val companySupportField : String,   // 기관 지원 분야
    val companySupportContent : String, // 기관 지원 내용
    val companyDigit : String,          // 기관 연락처
    val companyDepartment : String,     // 기관 담당부서
    val companyEmail : String,          // 기관 이메일
    val companyURL : String,            // 기관 홈페이지
    val eventTitle : String,            // 이벤트 명
    val eventApplyPeriodStart : String, // 신청 시작 날짜
    val eventApplyPeriodEnd : String,   // 신청 종료 날짜
    val eventApplyHow : String,         // 신청 방법
    val eventApplyProcess : String,     // 신청 절차
    val eventTestWay : String,          // 평가방법
    val eventSummary : String,          // 개요
    val markedCount : List<String>      // 북마크된 횟수
) {
}