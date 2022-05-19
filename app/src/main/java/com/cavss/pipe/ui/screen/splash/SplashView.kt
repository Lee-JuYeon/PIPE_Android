package com.cavss.pipe.ui.screen.splash

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cavss.pipe.R
import com.cavss.pipe.ui.custom.navigation.NavigatorDestination
import com.cavss.pipe.ui.custom.sheet.CustomSheet
import com.cavss.pipe.ui.custom.sheet.CustomSheetVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.text.Charsets.UTF_8

/*
 Splash
 1. 인터넷 연결 v
 2. 자동로그인
 4. gps 확인 v
 5. vpn 차단
 6. local db 확인
 7. 언어현지화
 8. 위치별 데이터 현지화
 9. 컬러모드
 */

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashView(
    navController: NavController
) {
//    val customDataStoreVM = CustomDataStoreVM()
//    val testRemember = remember { mutableStateOf("") }
//    customDataStoreVM.viewModelScope.launch(Dispatchers.IO) {
//        customDataStoreVM.setData("test", "테스트111".toByteArray(Charsets.UTF_8))
//        val test = customDataStoreVM.getData("test")
//        testRemember.value = String(test.value!!, Charsets.UTF_8)
//        Log.e("mException", "test getData : ${testRemember.value }")
//    }


    val isShowSheet = remember { mutableStateOf(true) }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        /*
        TODO :  1. 로고 이미지
                2. 프로세스 상태
                3. 백그라운드 작업
         */

        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            modifier = Modifier
                .clickable {
                    isShowSheet.value = !isShowSheet.value
                }
        )

        Text(
            text = "다음",
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            modifier = Modifier
                .clickable {
                    navController.navigate(NavigatorDestination.ToMain.route)
                }
        )


//        val scope = rememberCoroutineScope()
//        scope.launch(Dispatchers.Main) {
//            viewModel.setData("openCount" , byteArrayOf())
//        }
//        val appOpenCount = String(AESHelper.keystoreDecrypt()!!, Charsets.UTF_8).toInt()
        when(0){
            0 -> {
                CustomSheet(
                    onDismiss = {
                        Log.e("mException", "SplashView, CustomSheet, onDismiss // 사라짐~")
//                        LocalLifecycleOwner.current.lifecycleScope.launch(Dispatchers.IO){
//                            val encryptedCountingNumber = AESHelper.keystoreEncrypt("1".toByteArray(Charsets.UTF_8))
//                            localDB.settingDAO().updateOpenCount(encryptedCountingNumber)
//                        }
                    },
                    INNER_VIEW = { constraints: Constraints ->
                        SplashRequestView()
                    }
                )
            }
            else -> {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = Color.Black,
                    maxLines = 1,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(NavigatorDestination.ToMain.route)
                        }
                )
            }
        }
    }
}

//class BackgroundProcessing {
//    private var application : Application? = null
//    fun setApplication(set : Application) { this.application = set }
//
//    private var activity : Activity? = null
//    fun setActivity(set : Activity) { this.activity = set }
//
//    private fun setBackground(){
//        // 인터넷 셋팅
//        setInternetState()
//
//        // 현재 위치 설정
//        setLocation()
//    }
//
//    private fun setNofity(){
//        val string = ""
//    }
//
//    // 인터넷 셋팅
//    private lateinit var internetManager : InternetManager
//    private fun setInternetState(){
//        internetManager = InternetManager(activity as ConnectivityManager)
//        internetManager.observe(activity as LifecycleOwner) { isConnected: Boolean ->
//            Log.d("mException","네트워크 연결상태 : ${isConnected}")
//        }
//    }
//
//    // 위치 기능 뷰
//    private fun setLocation(){
//        val locationLiveData = LocationLiveData(activity!!)
//
//        locationLiveData.let {
//            it.requestPermission()
//            it.updateLocation()
//            it.observe(this@MainActivity) { mapDTO ->
//                /*
//                TODO :  1. adview에 위치정보 전달
//                 */
//                Log.i("mException", "MainActivity, setLocation, locationLiveData // adview에 위치정보 전달")
//                Log.d("mException", "로케이션 // 위도 : ${mapDTO.latitude}, 경도 : ${mapDTO.longitude}")
//            }
//        }
//    }
//}
