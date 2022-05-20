package com.cavss.pipe

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.cavss.pipe.backend.secure.AESHelper
import com.cavss.pipe.backend.secure.ZygoteInit
import com.cavss.pipe.ui.custom.navigation.NaviView
import com.cavss.pipe.ui.custom.sheet.CustomSheetVM
import com.cavss.pipe.ui.theme.PIPETheme
import com.cavss.pipe.vm.APIVM

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

    private fun setPrepare() {
        try {
            ZygoteInit.warmUpJCAproviders()
            AESHelper.keySetting()
//            CustomDataStore.getInstance(this.applicationContext)
        } catch (e: Exception) {
            Log.e("mException", "MainActivity, setPrepare // Esxception : ${e.message}")
        }
    }
}