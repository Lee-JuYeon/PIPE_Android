package com.cavss.pipe.ui.screen.main.setting.sub

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cavss.pipe.R
import com.cavss.pipe.ui.custom.icontitle.IconTitle

@Composable
fun SettingTitleView() {
    IconTitle(
        setImage = R.drawable.icon_settings,
        setSize = 40,
        setTitle = stringResource(id = R.string.setting_title),
        setTitleColour = Color.White,
        setTitleSize = 30,
        setModifier = Modifier
            .padding(10.dp)
    )
}