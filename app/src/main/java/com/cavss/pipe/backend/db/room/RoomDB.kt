package com.cavss.pipe.backend.db.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cavss.pipe.backend.secure.AESHelper
import com.cavss.pipe.model.user.UserDAO
import com.cavss.pipe.model.user.UserDTO
import com.cavss.pipe.util.datatype.TypeConverting
import java.util.*
import java.util.concurrent.Executors

@Database(
    entities = arrayOf(
        UserDTO::class,
    ),
    version =  1,
    exportSchema = true
)
@TypeConverters(TypeConverting::class)
abstract class RoomDB : RoomDatabase() {

    abstract fun userDAO() : UserDAO

    // 파라미터를 받는 싱글톤 클래스를 만들려면 companion obejct를 이용한다.
    companion object{
        @Volatile private var INSTANCE : RoomDB? = null
        private var DB_name : String = "RoomDB"
        private var context : Context? = null
        fun setInstance(_context: Context): RoomDB {
            var instance = INSTANCE
            return when(instance == null){
                true -> {
                    instance = INSTANCE ?: synchronized(this){
                        INSTANCE ?: Room.databaseBuilder(
                            _context,
                            RoomDB::class.java,
                            DB_name
                        ).addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                ioThread {
                                    try {
                                        val encryptedEmail  = AESHelper.keystoreEncrypt("email".toByteArray(Charsets.UTF_8))
                                        val encryptedPW  = AESHelper.keystoreEncrypt("pw".toByteArray(Charsets.UTF_8))
                                        val encryptedColourTheme  = AESHelper.keystoreEncrypt("PINK".toByteArray(Charsets.UTF_8))
                                        val encryptedUserUID = AESHelper.keystoreEncrypt("userUID_${UUID.randomUUID()}".toByteArray(Charsets.UTF_8))
                                        INSTANCE?.userDAO()?.insert(
                                            UserDTO(
                                                email = encryptedEmail,
                                                pw = encryptedPW,
                                                autoLogin = false,
                                                colourTheme = encryptedColourTheme,
                                                userUID = encryptedUserUID
                                            )
                                        )
                                    }catch (e:Exception){
                                        Log.e("mException", "RoomDB, setInstance, addCallBack, ioThread // Exception : ${e.message}")
                                    }
                                }
                            }})
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                    context = _context
                    instance
                }
                false -> {
                    context = _context
                    instance
                }
            }
        }

        fun INSTANCE(): RoomDB{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(
                    context!!,
                    RoomDB::class.java,
                    DB_name
                ).fallbackToDestructiveMigration().build()
            }
        }

        private val IO_EXECUTOR = Executors.newSingleThreadExecutor()
        private fun ioThread(f : () -> Unit) {
            IO_EXECUTOR.execute(f)
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}