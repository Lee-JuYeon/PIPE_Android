package com.cavss.pipe.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.cavss.pipe.backend.db.room.RoomDB
import com.cavss.pipe.model.user.UserDAO
import com.cavss.pipe.model.user.UserDTO
import java.util.*

//class DBRepository(private val dao : UserDAO) {
//    private val userDAO : UserDAO by lazy {
//        val db = RoomDB.getDB()
//        db.userDAO()
//    }
//
//    private val getUserDTO : LiveData<UserDTO> by lazy {
//        userDAO.getUserDTO()
//    }
//
//    fun updateAutoLogin(isAutoLogin : Boolean) : Observable<Unit>{
//        return Observable.fromCallable {  }
//    }
//}