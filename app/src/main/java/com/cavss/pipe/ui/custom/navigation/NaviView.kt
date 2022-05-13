package com.cavss.pipe.ui.custom.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cavss.pipe.ui.screen.login.LoginView
import com.cavss.pipe.ui.screen.main.MainView
import com.cavss.pipe.ui.screen.splash.SplashView

@Composable
fun NaviView(
    setActivityContext : Context
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigatorDestination.ToMain.route) {
        composable(route = NavigatorDestination.ToSplash.route){
            SplashView(
                navController = navController
            )
        }
        composable(route = NavigatorDestination.ToLogin.route) {
            LoginView(
                navController = navController
            )
        }
        composable(route = NavigatorDestination.ToMain.route){
            MainView(
                navController = navController
            )
        }
    }
}

/*
TODO :  1. 앱 처음 실행치 권한요청
        2. 앱 처음 실행인지 아닌지 boolean으로 카운트
        3. 자동로그인 체크
 */