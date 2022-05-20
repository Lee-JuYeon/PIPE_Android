package com.cavss.pipe.vm

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cavss.pipe.model.item.ApiDetailItemDTO
import com.cavss.pipe.ui.screen.main.apilist.APIListType

/*
SingleTon 싱글톤
싱글톤 패턴이란 ?
 -  클래스에 접근하려면 변수를 설정하고, 변수 설정과 동시에 인스턴스가 자동적으로 생성된다.
 -  근데 변수를 선언 할 때마다 메모리는 점점 늘어나게 되는데,
    싱글톤을 사용하게되면 변수 선언을 딱 한번만 설정하고도 모든 방면에서 해당 싱글톤을 가지고 클래스 내 데이터를 공유-사용이 가능하다.
 -  아무데서나 접근이 가능한데, 인스턴스는 하나만 가지고 있는 쵸 카와이한 메모리절약 '싱글턴 패턴'
 */
object APIVM : ViewModel() {

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
    fun getData(type : APIListType) : LiveData<List<ApiDetailItemDTO>>{
        return when(type){
            APIListType.MONEY_GOVERNMENT -> {
                _APImoneyGovernmentList
            }
            APIListType.MONEY_SCHOLARSHIP -> {
                _APImoneyGovernmentList
            }
            APIListType.MONEY_ACTIVITY -> {
                _APImoneyGovernmentList
            }
            APIListType.HOUSE_PREMIUM -> {
                _APImoneyGovernmentList
            }
            APIListType.HOUSE_MYHOME -> {
                _APImoneyGovernmentList
            }
            APIListType.HOUSE_CHECKLIST -> {
                _APImoneyGovernmentList
            }
            APIListType.STARTUP_EVENT -> {
                _APImoneyGovernmentList
            }
            APIListType.STARTUP_LAB -> {
                _APImoneyGovernmentList
            }
            APIListType.RECRUIT_EVENT -> {
                _APImoneyGovernmentList
            }
            APIListType.RECRUIT_RECRUIT -> {
                _APImoneyGovernmentList
            }
        }
    }
}

/*
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
        @Volatile
        private var instance: APIVM? = null // volatile = java 변수를 mina memory에 저장, 메인 메모리에서 가져와서 사용.

        private var context: Context? = null

        @JvmStatic
        fun getInstacne(_context: Context): APIVM {
            /*
            1. 인스턴스를 가져올 때 null체크
            2. snychronized 븦록에서도 null 체크
            3. 멀티쓰래드 환경에서도 한 개의 instance를 유지 가능
             */
            return instance ?: synchronized(this) { //APIVM이  null 일 결우 synchronized.
                instance ?: APIVM().also {
                    Log.e("mException", "컨텍스트 작동중 1")
                    context = _context //APIVM이 null일 경우, context 넣어줌
                    Log.e("mException", "컨텍스트 작동중 context ${context}")
                    instance = it // context를 가진 APIVM을 instance 변수에 치환함.
                    Log.e("mException", "컨텍스트 작동중 instance ${instance}")

                }
            }
        }

        var nowData : LiveData<ApiDetailItemDTO> = instance?.currentData!!
        fun setCurrentData(set: ApiDetailItemDTO) {
            instance!!.setCurrentData(set = set)
        }
        fun getData(type : APIListType) : LiveData<List<ApiDetailItemDTO>>{
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
 */