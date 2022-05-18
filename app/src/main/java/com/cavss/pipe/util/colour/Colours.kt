package com.cavss.pipe.util.colour

import androidx.compose.ui.graphics.Color

class Colours {

    val pinkPallet = hashMapOf(
        ColoursPurposeType.BACKGROUND to Color(0xFF6263A3),
        ColoursPurposeType.DARKNESS to Color(0xFF434176),
        ColoursPurposeType.LIGHTNESS to Color(0xFFd8e6ef),
        ColoursPurposeType.FIRST_POINT to Color(0xFFeecfd8),
        ColoursPurposeType.SECOND_POINT to Color(0xFFe7e688)
    )

    val redPallet = hashMapOf(
        ColoursPurposeType.BACKGROUND to Color(0xFF6263A3),
        ColoursPurposeType.DARKNESS to Color(0xFF3c3d3f),
        ColoursPurposeType.LIGHTNESS to Color(0xFFd8d5cc),
        ColoursPurposeType.FIRST_POINT to Color(0xFFe95259),
        ColoursPurposeType.SECOND_POINT to Color(0xFFbd6b9b)
    )

    private var setPalleteType : ColoursPalleteType? = null
    fun setPalletType(set : ColoursPalleteType) {
        this.setPalleteType = set
    }

    fun getColourList() : HashMap<ColoursPurposeType, Color> = when(setPalleteType){
        ColoursPalleteType.PINK -> pinkPallet
        ColoursPalleteType.RED -> redPallet
        else -> pinkPallet
    }

    fun getColourList(type: ColoursPalleteType) : HashMap<ColoursPurposeType, Color> {
        return when(type) {
            ColoursPalleteType.PINK -> pinkPallet
            ColoursPalleteType.RED -> redPallet
            else -> pinkPallet
        }
    }
}