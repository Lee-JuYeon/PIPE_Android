package com.cavss.pipe.model.dock

import com.cavss.pipe.R

sealed class DockModel (
    var itemID : String,
    val itemImage : Int,
    val itemTitle : Int,
){
    object Community : DockModel(itemID = "COMMUNITY", itemImage = R.drawable.ic_launcher_background, itemTitle = R.string.dock_item_community)
    object Money : DockModel(itemID = "MONEY", itemImage = R.drawable.ic_launcher_background, itemTitle = R.string.dock_item_money)
    object House : DockModel(itemID = "HOUSE", itemImage =  R.drawable.ic_launcher_background, itemTitle = R.string.dock_item_house)
    object StartUp : DockModel(itemID = "STARTUP", itemImage = R.drawable.ic_launcher_background, itemTitle = R.string.dock_item_startup)
    object Recruit : DockModel(itemID = "RECRUIT", itemImage = R.drawable.ic_launcher_background, itemTitle = R.string.dock_item_recruit)
    object Setting : DockModel(itemID = "SETTING", itemImage = R.drawable.ic_launcher_background, itemTitle = R.string.dock_item_setting)

    object DOCK_LIST {
        val dockList = listOf(
//            Community, Money, House, StartUp, Recruit, Setting
            Money, House, StartUp, Recruit, Setting
        )
    }
}