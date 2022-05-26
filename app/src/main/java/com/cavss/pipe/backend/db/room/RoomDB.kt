package com.cavss.pipe.backend.db.room

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cavss.pipe.model.user.UserDAO
import com.cavss.pipe.model.user.UserDTO

@Database(
    entities = arrayOf(
        UserDTO::class,
    ), version =  1, exportSchema = true)
abstract class RoomDB : RoomDatabase() {

    abstract fun userDAO() : UserDAO

    // 파라미터를 받는 싱글톤 클래스를 만들려면 companion obejct를 이용한다.
    companion object{
        @Volatile private var instance : RoomDB? = null
        private var DB_name : String = "RoomDB"
        fun getInstance(context: Context): RoomDB {
            return instance ?: synchronized(this){
                instance ?: Room.databaseBuilder(
                    context,
                    RoomDB::class.java,
                    DB_name
                ).build()
            }
        }


        fun destroyDataBase(){
            instance = null
        }
    }
}