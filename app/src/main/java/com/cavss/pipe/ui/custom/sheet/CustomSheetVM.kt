package com.cavss.pipe.ui.custom.sheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cavss.pipe.model.item.ApiDetailItemDTO

class CustomSheetVM : ViewModel() {
    private val _apiItemDTO = MutableLiveData(ApiDetailItemDTO(
        "",
        "",
        false,
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        listOf()
    ))
    val apiDetailItemDTO: LiveData<ApiDetailItemDTO> = _apiItemDTO
    fun setAPIItemDTO(set : ApiDetailItemDTO) {
        _apiItemDTO.value = set
    }

    private val _innerViewType = MutableLiveData(CustomSheetType.PERMISSION_REQUEST.type)
    val innerViewType : LiveData<String> = _innerViewType
    fun setInnerViewType(set : String){
        _innerViewType.value = set
    }

    private val _isShow = MutableLiveData(false)
    val isShow : LiveData<Boolean> = _isShow
    fun setShowBottomSheetState(set : Boolean){
        _isShow.value = set
    }
}