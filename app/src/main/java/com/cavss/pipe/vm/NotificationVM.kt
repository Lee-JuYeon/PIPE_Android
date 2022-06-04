package com.cavss.pipe.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cavss.pipe.model.setting.NotificationDTO


class NotificationVM() : ViewModel(){
    private val _notificationList : MutableLiveData<List<NotificationDTO>> = MutableLiveData(listOf())
    val notificationList: LiveData<List<NotificationDTO>> = _notificationList
    fun loadNotificationList(set : List<NotificationDTO>){
        _notificationList.postValue(set)
    }

    init{
        loadNotificationList(
            listOf(
                NotificationDTO("2020.02.02 02:02", "공지사항 제목1", "공지사항 내용1"),
                NotificationDTO("2020.02.02 02:02", "공지사항 제목22", "공지사항 내용22"),
                NotificationDTO("2020.02.02 02:02", "공지사항 제목333", "공지사항 내용333"),
                NotificationDTO("2020.02.02 02:02", "공지사항 제목4444", "공지사항 내용4444"),
                NotificationDTO("2020.02.02 02:02", "공지사항 제목1", "공지사항 내용1"),
                NotificationDTO("2020.02.02 02:02", "공지사항 제목22", "공지사항 내용22"),
                NotificationDTO("2020.02.02 02:02", "공지사항 제목333", "공지사항 내용333"),
                NotificationDTO("2020.02.02 02:02", "공지사항 제목4444", "공지사항 내용4444")
            )
        )
    }
}