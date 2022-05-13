package com.cavss.pipe.ui.custom.navigation

sealed class NavigatorDestination(val route : String) {
    object ToSplash : NavigatorDestination("splash")
    object ToLogin : NavigatorDestination("login")
    object ToMain : NavigatorDestination("main")
}