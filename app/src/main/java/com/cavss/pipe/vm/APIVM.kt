package com.cavss.pipe.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cavss.pipe.model.item.ApiDetailItemDTO

class APIVM : ViewModel() {
    private val _currentData = MutableLiveData(ApiDetailItemDTO(
        "", "", false, "0", "건설학과", "소프트웨어 개발자", "999", "쌤쑹", "민간", "", "", "", "", "", "", "첫번째 테스트 이벤트 타이틀입니다.", "1990.07.29 07:29", "1994.11.18 11:18", "", "", "", "", listOf()
    ))
    val currentData: LiveData<ApiDetailItemDTO> = _currentData
    fun setCurrentData(set : ApiDetailItemDTO) {
        _currentData.value = set
    }

    private val _APImoneyGovernmentList = MutableLiveData(listOf(
        ApiDetailItemDTO("", "", false, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", listOf())
    ))
    val getAPImoneyGovernmentList : LiveData<List<ApiDetailItemDTO>> = _APImoneyGovernmentList
    val apiMoneyGovernmentList : LiveData<List<ApiDetailItemDTO>> = _APImoneyGovernmentList


    init {

    }
}