package com.cavss.pipe.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cavss.pipe.model.item.ApiDetailItemDTO
import com.cavss.pipe.ui.custom.sheet.CustomSheetType

/*
TODO : vm을 싱글톤으로 구현하려 했음.
 vm을 싱글톤으로 구현하는게 정신나간 생각.
 싱글톤으로 그냥 따로 구현하자.
 아니다.. 그냥 뷰모델로 하자.... 머리 터질 것 같다
 */

class CustomSheetVM : ViewModel() {
    private var _isShow : MutableLiveData<Boolean> = MutableLiveData(false)
    val isShow : LiveData<Boolean> = _isShow
    fun setSheetVisible(set : Boolean){
        _isShow.postValue(set)
    }

    private var _innerType : MutableLiveData<CustomSheetType> = MutableLiveData(CustomSheetType.API_DETAIL)
    val innerType : LiveData<CustomSheetType> = _innerType
    fun setInnerType(set : CustomSheetType){
        _innerType.postValue(set)
    }

    private var _currentData : MutableLiveData<ApiDetailItemDTO> = MutableLiveData(ApiDetailItemDTO("", "", "2년차", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", listOf()))
    val currentData : LiveData<ApiDetailItemDTO> = _currentData
    fun setCurrentData(set : ApiDetailItemDTO){
        _currentData.postValue(set)
    }

    private var _collectedData : MutableLiveData<Any> = MutableLiveData(Any())
    val collectedData : LiveData<Any> = _collectedData
    fun setCollectedData(set : Any){
        _collectedData.postValue(set)
    }
}
