package com.cavss.pipe.ui.custom.sheet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cavss.pipe.model.item.ApiDetailItemDTO
import com.cavss.pipe.vm.APIVM

object CustomSheetVM : ViewModel() {
    private val _apiItemDTO = MutableLiveData(ApiDetailItemDTO("", "", false, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", listOf()))
    val apiItemDTO : LiveData<ApiDetailItemDTO> = _apiItemDTO
    fun setAPIItemDTO(set : ApiDetailItemDTO) {
        _apiItemDTO.value = set
    }

    private val _innerViewType = MutableLiveData(CustomSheetType.PERMISSION_REQUEST)
    val innerViewType : LiveData<CustomSheetType> = _innerViewType
    fun setInnerViewType(set : CustomSheetType){
        _innerViewType.value = set
    }

    private val _isShow = MutableLiveData(false)
    val isShow : LiveData<Boolean> = _isShow
    fun setShowBottomSheetState(set : Boolean){
        _isShow.value = set
    }
}

/*

class CustomSheetVM : ViewModel() {
    private val _apiItemDTO = MutableLiveData(ApiDetailItemDTO("", "", false, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", listOf()))
    fun setAPIItemDTO(set : ApiDetailItemDTO) {
        _apiItemDTO.value = set
    }

    private val _innerViewType = MutableLiveData(CustomSheetType.PERMISSION_REQUEST.type)
    fun setInnerViewType(set : String){
        _innerViewType.value = set
    }

    private val _isShow = MutableLiveData(false)
    fun setShowBottomSheetState(set : Boolean){
        _isShow.value = set
    }

    init {

    }

    companion object {
        private var instance : CustomSheetVM? = null
        fun getInstance() : CustomSheetVM {
            return instance ?: synchronized(this) { //ACustomSheetVM이  null 일 결우 synchronized.
                instance ?: CustomSheetVM().also {
                    instance = it // CustomSheetVMM을 instance 변수에 치환함.
                }
            }
        }

        val isShow : LiveData<Boolean> = instance?._isShow ?: MutableLiveData<Boolean>(false)
        fun setShowBottomSheetState(set : Boolean){
            try{
                instance?.setShowBottomSheetState(set = set)
            }catch (e:Exception){
                Log.e("mException", "CustomSheetVM. companion, setShowBottomSheetState // Exception : ${e.message}")
            }
        }

        val innerViewType : LiveData<String> = instance?._innerViewType!!
        fun setInnerViewType(set : String){
            try{
                instance?.setInnerViewType(set = set)
            }catch (e:Exception){
                Log.e("mException", "CustomSheetVM. companion, setInnerViewType // Exception : ${e.message}")
            }
        }

        val apiDetailItemDTO: LiveData<ApiDetailItemDTO> = instance?._apiItemDTO!!
        fun setAPIItemDTO(set : ApiDetailItemDTO){
            try{
                instance?.setAPIItemDTO(set = set)
            }catch (e:Exception){
                Log.e("mException", "CustomSheetVM. companion, setAPIItemDTO // Exception : ${e.message}")
            }
        }
    }

}
 */