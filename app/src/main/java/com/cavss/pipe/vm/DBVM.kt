package com.cavss.pipe.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cavss.pipe.App
import com.cavss.pipe.backend.db.room.RoomDB
import com.cavss.pipe.model.user.UserDTO

class DBVM : ViewModel() {
    private val db : RoomDB = RoomDB.getDB()

    fun getUserDTO() : LiveData<UserDTO> {
        return db.userDAO().getUserDTO()
    }

    suspend fun updateAutoLogin(isAutoLogin : Boolean){
        db.userDAO().updateAutoLogin(isAutoLogin = isAutoLogin)
    }

    suspend fun updateColourTheme(colourTheme : HashMap<String, ByteArray>){
        db.userDAO().updateColourTheme(newColourTheme = colourTheme)
    }

    suspend fun updatePW(pw :  HashMap<String, ByteArray>){
        db.userDAO().updatePW(newPW = pw)
    }
}
