package com.cavss.pipe

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cavss.pipe.backend.db.room.RoomDB
import com.cavss.pipe.backend.secure.AESHelper
import com.cavss.pipe.backend.secure.ZygoteInit
import com.cavss.pipe.model.user.UserDTO
import com.cavss.pipe.ui.custom.adview.google.GoogleAdUtil
import com.cavss.pipe.ui.custom.navigation.NaviView
import com.cavss.pipe.ui.theme.PIPETheme
import com.cavss.pipe.vm.DBVM
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setPrepare()
            PIPETheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NaviView(this)
                }
            }
        }
    }

    private var dbVM : DBVM = ViewModelProvider()
    private fun setPrepare() {
        try {
            // DB
            App.INSTANCE
            RoomDB.setInstance(App.INSTANCE.applicationContext)

            dbVM.value.getUserDTO().observe(this, Observer {  userDTO : UserDTO ->
                try {
                    Log.e("mException", "저장된 데이터 : ${userDTO}")
                }catch (e:Exception){
                    /*
                    Cannot access database on the main thread since it may potentially lock the UI for a long period of time.
                    = 메인 UI 화면이 오랫동안 멈춰있을 수도 있기 때문에, 메인 쓰레드에서는 데이터베이스에 접근할 수 없습니다 -> lifecycleScope.launch(Dispatchers.IO) 이용
                     */
                    Log.e("mException", "MainActivity, setPrepare, dbVM // Exception : ${e.message}")
                }catch (e:NoSuchElementException){
                    Log.e("mException", "MainActivity, setPrepare, dbVM // NoSuchElementException : ${e.message}")
                }
            })

            // 광고
            MobileAds.initialize(this@MainActivity)
            GoogleAdUtil.let {
                it.loadInterstitial(this@MainActivity)
                it.addInterstitialCallbacks(this@MainActivity)
            }
        } catch (e: Exception) {
            Log.e("mException", "MainActivity, setPrepare // Esxception : ${e.message}")
        }
    }
}