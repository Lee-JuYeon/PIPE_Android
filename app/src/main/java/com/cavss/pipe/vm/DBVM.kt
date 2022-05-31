package com.cavss.pipe.vm

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cavss.pipe.App
import com.cavss.pipe.backend.db.room.RoomDB
import com.cavss.pipe.backend.secure.AESHelper
import com.cavss.pipe.model.user.UserDTO
import com.cavss.pipe.util.colour.ColoursPalleteType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.HashMap

class DBVM(context : Activity) : ViewModel() {
    private val db : RoomDB = RoomDB.setInstance(context)
    private fun emptyDataChecker(){
        try{
            if (getUserDTO().value?.userUID != null){
                viewModelScope.launch(Dispatchers.IO) {
                    val encryptedEmail  = AESHelper.keystoreEncrypt("email".toByteArray(Charsets.UTF_8))
                    val encryptedPW  = AESHelper.keystoreEncrypt("pw".toByteArray(Charsets.UTF_8))
                    val encryptedColourTheme  = AESHelper.keystoreEncrypt("PINK".toByteArray(Charsets.UTF_8))
                    val encryptedUserUID = AESHelper.keystoreEncrypt("userUID_${UUID.randomUUID()}".toByteArray(Charsets.UTF_8))
                    db.userDAO().insert(
                        UserDTO(
                            email = encryptedEmail,
                            pw = encryptedPW,
                            autoLogin = false,
                            colourTheme = encryptedColourTheme,
                            userUID = encryptedUserUID
                        )
                    )
                }
            }
        }catch (e:Exception){
            Log.e("mException", "DBVM, emptyDataChecker // Exception : ${e.message}")
        }
    }

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

    init {
        emptyDataChecker()
        db.userDAO().getUserDTO().observe(context as LifecycleOwner){
            Log.e("mException", "데이터 : ${it}")
        }
    }
}
