package com.cavss.pipe.backend.db.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import com.cavss.pipe.util.colour.Colours
import com.cavss.pipe.util.colour.ColoursPalleteType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DataStoreManager private constructor() : ViewModel(){

    // 파라미터를 받는 싱글톤 클래스를 만들려면 companion obejct를 이용한다.
    companion object {
        @Volatile private var instance: DataStoreManager? = null // volatile = java 변수를 mina memory에 저장, 메인 메모리에서 가져와서 사용.
        private var context: Context? = null

        @JvmStatic
        fun setInstance(_context: Context): DataStoreManager {
            /*
            1. 인스턴스를 가져올 때 null체크
            2. snychronized 븦록에서도 null 체크
            3. 멀티쓰래드 환경에서도 한 개의 instance를 유지 가능
             */
            return instance ?: synchronized(this) { //DataStoreManager 이  null 일 결우 synchronized.
                instance ?: DataStoreManager().also {
                    context = _context //DataStoreManager이 null일 경우, context 넣어줌
                    instance = it // context를 가진 DataStoreManager을 instance 변수에 치환함.
                }
            }
        }

        private val Context.db : DataStore<Preferences> by preferencesDataStore(name = "UserInfo")
        val mKey = stringPreferencesKey("colour")
        val getData : Flow<String?> = context!!.db.data.map { preferences ->
            preferences[mKey] ?: ColoursPalleteType.PINK.concept
        }

        suspend fun setData(data : String){
            context!!.db.edit { preferences ->
                preferences[mKey] = data
            }
        }
    }
}