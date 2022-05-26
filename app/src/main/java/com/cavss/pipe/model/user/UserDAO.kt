package com.cavss.pipe.model.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {
    @Insert
    fun insert(userDTO : UserDTO)

    @Query("select * from UserDTO")
    fun getUserDTO() : LiveData<UserDTO>

    @Query("update UserDTO set autoLogin = :isAutoLogin where id = 0")
    suspend fun updateAutoLogin(isAutoLogin : Boolean)

    @Query("update UserDTO set colourTheme = :newColourTheme where id = 0")
    suspend fun updateColourTheme(newColourTheme : HashMap<String, ByteArray>)

    fun updateIDPW()
    fun updateUserInfo()
}