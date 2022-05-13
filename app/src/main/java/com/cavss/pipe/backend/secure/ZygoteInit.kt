package com.cavss.pipe.backend.secure

import android.util.Log

object ZygoteInit {
    fun warmUpJCAproviders(){
        try{
            AndroidKeyStoreProvider.install()
        }catch(e:Exception){
            Log.e("mException", "ZygoteInit, warmUpJCAproviders 설정 중 Exception : ${e.message}")
        }
    }
}