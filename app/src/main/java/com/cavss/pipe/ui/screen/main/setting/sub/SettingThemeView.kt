package com.cavss.pipe.ui.screen.main.setting.sub

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cavss.pipe.R
import com.cavss.pipe.ui.custom.icontitle.IconTitle
import com.cavss.pipe.util.colour.Colours
import com.cavss.pipe.util.colour.ColoursPalleteType
import com.cavss.pipe.util.colour.ColoursPurposeType

@Composable
fun SettingThemeView() {
    val isExpendable = remember{ mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(20.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 200,
                    easing = LinearEasing
                )
            )
    ) {
        IconTitle(
            setImage = R.drawable.icon_theme,
            setSize = 30,
            setTitle = stringResource(id = R.string.setting_theme),
            setTitleColour = Color.White,
            setTitleSize = 20,
            setModifier = Modifier
                .clickable {
                    isExpendable.value = !isExpendable.value
                }
        )

        if (isExpendable.value){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                palletItem(setType = ColoursPalleteType.PINK, setExpendable = isExpendable)
                palletItem(setType = ColoursPalleteType.RED, setExpendable = isExpendable)
            }
        }
    }
}

@Composable
fun palletItem(setType : ColoursPalleteType, setExpendable : MutableState<Boolean>){
    val colours = Colours()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                setExpendable.value = false
            }
    ) {
        Text(
            text = setType.concept,
            color = Color.White,
            maxLines = 1,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.noto_normal)),
            overflow = TextOverflow.Ellipsis
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .border(1.dp, Color.White)
        ) {
            Box(
                modifier = Modifier
                    .background(colours.getColourList(setType)[ColoursPurposeType.BACKGROUND]!!)
                    .size(20.dp)
            )
            Box(
                modifier = Modifier
                    .background(colours.getColourList(setType)[ColoursPurposeType.FIRST_POINT]!!)
                    .size(20.dp)
            )
            Box(
                modifier = Modifier
                    .background(colours.getColourList(setType)[ColoursPurposeType.SECOND_POINT]!!)
                    .size(20.dp)
            )
            Box(
                modifier = Modifier
                    .background(colours.getColourList(setType)[ColoursPurposeType.DARKNESS]!!)
                    .size(20.dp)
            )
            Box(
                modifier = Modifier
                    .background(colours.getColourList(setType)[ColoursPurposeType.LIGHTNESS]!!)
                    .size(20.dp)
            )
        }
    }
}
