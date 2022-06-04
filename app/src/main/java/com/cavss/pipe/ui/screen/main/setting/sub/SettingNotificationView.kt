package com.cavss.pipe.ui.screen.main.setting.sub

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.*
import com.cavss.pipe.R
import com.cavss.pipe.model.setting.NotificationDTO
import com.cavss.pipe.ui.custom.icontitle.IconTitle
import com.cavss.pipe.ui.custom.sheet.CustomSheetType
import com.cavss.pipe.util.colour.Colours
import com.cavss.pipe.util.colour.ColoursPurposeType
import com.cavss.pipe.vm.CustomSheetVM
import com.cavss.pipe.vm.NotificationVM

@Composable
fun SettingNotificationView(
    setCustomSheetVM: CustomSheetVM
) {
    IconTitle(
        setImage = R.drawable.icon_notification,
        setSize = 30,
        setTitle = stringResource(id = R.string.setting_notification),
        setTitleColour = Color.White,
        setTitleSize = 20,
        setModifier = Modifier
            .padding(20.dp)
            .clickable {
                setCustomSheetVM.let {
                    it.setInnerType(CustomSheetType.NOTIFICATION_DETAIL)
                    it.setSheetVisible(true)
                }
            }
    )
}

//https://doitddo.tistory.com/98
//class NotificationVM(private val repository : NotificationRepository) : ViewModel(){
//    private val _notificationList : MutableLiveData<List<NotificationDTO>> = MutableLiveData()
//    val notificationList: LiveData<List<NotificationDTO>> = _notificationList
//    private fun loadNotificationList(){
//        // repositoy로 부터 데이터 획득 ->  livedata에 postValue.
//        repository.getNotificationList().let {
//            _notificationList.postValue(it)
//        }
//    }
//
//    private val onItemClick : MutableLiveData<NotificationDTO> = MutableLiveData()
//    fun onItemClick(position : Int){
//        _notificationList.value?.getOrNull(position)?.let {
//            onItemClick.postValue(it)
//        }
//    }
//
//    init{
//        loadNotificationList()
//    }
//}

//interface NotificationRepository {
//    fun getNotificationList() : List<NotificationDTO>
//}
//
//class DummyNotificationRepositoryImpl : NotificationRepository {
//    override fun getNotificationList(): List<NotificationDTO> = listOf(
//        NotificationDTO("2020.02.02 02:02", "공지사항 제목1", "공지사항 내용1"),
//        NotificationDTO("2020.02.02 02:02", "공지사항 제목22", "공지사항 내용22"),
//        NotificationDTO("2020.02.02 02:02", "공지사항 제목333", "공지사항 내용333"),
//        NotificationDTO("2020.02.02 02:02", "공지사항 제목4444", "공지사항 내용4444"),
//        NotificationDTO("2020.02.02 02:02", "공지사항 제목55555", "공지사항 내용55555"),
//    )
//}
//
//class NotificationViewModelFactory(val repository : NotificationRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(NotificationVM::class.java)){
//            return NotificationVM(repository) as T
//        }
//        throw IllegalArgumentException("vm클래스를 찾을 수 없음. :: ${modelClass::class.java.simpleName}")
//    }
//}

