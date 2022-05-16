package com.cavss.pipe.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cavss.pipe.model.item.ApiDetailItemDTO
import com.cavss.pipe.ui.screen.main.apilist.APIListType

class APIVM private constructor() : ViewModel() {

    private val _currentData = MutableLiveData(ApiDetailItemDTO(
        "", "", false, "0", "건설학과", "소프트웨어 개발자", "999", "쌤쑹", "민간", "", "", "", "", "", "", "첫번째 테스트 이벤트 타이틀입니다.", "1990.07.29 07:29", "1994.11.18 11:18", "", "", "", "", listOf()
    ))
    val currentData: LiveData<ApiDetailItemDTO> = _currentData
    fun setCurrentData(set: ApiDetailItemDTO) {
        _currentData.value = set
    }

    private val _APImoneyGovernmentList = MutableLiveData(listOf(
        ApiDetailItemDTO("", "", false, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", listOf())
    ))
    val getApiMoneyGovernment : LiveData<List<ApiDetailItemDTO>> = _APImoneyGovernmentList
    val getApiMoneyScholarship : LiveData<List<ApiDetailItemDTO>> = _APImoneyGovernmentList
    val getApiMoneyActivity : LiveData<List<ApiDetailItemDTO>> = _APImoneyGovernmentList
    val getApiHousePremium : LiveData<List<ApiDetailItemDTO>> = _APImoneyGovernmentList
    val getApiHouseMyhome : LiveData<List<ApiDetailItemDTO>> = _APImoneyGovernmentList
    val getApiHouseChecklist : LiveData<List<ApiDetailItemDTO>> = _APImoneyGovernmentList
    val getApiStartupEvent : LiveData<List<ApiDetailItemDTO>> = _APImoneyGovernmentList
    val getApiStartupLab : LiveData<List<ApiDetailItemDTO>> = _APImoneyGovernmentList
    val getApiRecruitEvent : LiveData<List<ApiDetailItemDTO>> = _APImoneyGovernmentList
    val getApiRecruitRecruit : LiveData<List<ApiDetailItemDTO>> = _APImoneyGovernmentList

    init {
        when(""){
            APIListType.MONEY_GOVERNMENT.type -> {
            }
            APIListType.MONEY_SCHOLARSHIP.type -> {

            }
            APIListType.MONEY_ACTIVITY.type -> {

            }
            APIListType.HOUSE_PREMIUM.type -> {

            }
            APIListType.HOUSE_MYHOME.type -> {

            }
            APIListType.HOUSE_CHECKLIST.type -> {

            }
            APIListType.STARTUP_EVENT.type -> {

            }
            APIListType.STARTUP_LAB.type -> {

            }
            APIListType.RECRUIT_EVENT.type -> {

            }
            APIListType.RECRUIT_RECRUIT.type -> {

            }
        }
    }

    // 파라미터를 받는 싱글톤 클래스를 만들려면 companion obejct를 이용한다.
    companion object {
        private var instance: APIVM? = null
        private lateinit var context: Context

        fun getInstacne(_context: Context): APIVM {
            return instance ?: synchronized(this) { //APIVM이  null 일 결우 synchronized.
                instance ?: APIVM().also {
                    context = _context //APIVM이 null일 경우, context 넣어줌
                    instance = it // context를 가진 APIVM을 instance 변수에 치환함.

                }
            }
        }

        fun getData(type : APIListType) : LiveData<List<ApiDetailItemDTO>> {
            return when(type){
                APIListType.MONEY_GOVERNMENT -> {
                    instance!!._APImoneyGovernmentList
                }
                APIListType.MONEY_SCHOLARSHIP -> {
                    instance!!._APImoneyGovernmentList
                }
                APIListType.MONEY_ACTIVITY -> {
                    instance!!._APImoneyGovernmentList
                }
                APIListType.HOUSE_PREMIUM -> {
                    instance!!._APImoneyGovernmentList
                }
                APIListType.HOUSE_MYHOME -> {
                    instance!!._APImoneyGovernmentList
                }
                APIListType.HOUSE_CHECKLIST -> {
                    instance!!._APImoneyGovernmentList
                }
                APIListType.STARTUP_EVENT -> {
                    instance!!._APImoneyGovernmentList
                }
                APIListType.STARTUP_LAB -> {
                    instance!!._APImoneyGovernmentList
                }
                APIListType.RECRUIT_EVENT -> {
                    instance!!._APImoneyGovernmentList
                }
                APIListType.RECRUIT_RECRUIT -> {
                    instance!!._APImoneyGovernmentList
                }
            }
        }
    }
}