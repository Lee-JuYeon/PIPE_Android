package com.cavss.pipe.util.colour

import androidx.compose.ui.graphics.Color

class Colours {
    private var setPalleteType : ColoursPalleteType? = null
    fun setPalletType(set : ColoursPalleteType) {
        this.setPalleteType = set
    }

    fun getColourList() : HashMap<ColoursPurposeType, Color> = when(setPalleteType){
        ColoursPalleteType.PURPLE -> {
            hashMapOf(
                ColoursPurposeType.BACKGROUND to Color(0xFFFFF6FF),
                ColoursPurposeType.DARKNESS to Color(0xFF),
                ColoursPurposeType.LIGHTNESS to Color(0xFF),
                ColoursPurposeType.FIRST_POINT to Color(0xFF9463C5),
                ColoursPurposeType.SECOND_POINT to Color(0xFF00C9AD)
            )
        }
        else -> {
            hashMapOf(
                ColoursPurposeType.BACKGROUND to Color(0xFFFFF6FF),
                ColoursPurposeType.DARKNESS to Color(0xFF),
                ColoursPurposeType.LIGHTNESS to Color(0xFF),
                ColoursPurposeType.FIRST_POINT to Color(0xFF9463C5),
                ColoursPurposeType.SECOND_POINT to Color(0xFF00C9AD)
            )
        }
    }
}