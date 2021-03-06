package com.cavss.pipe.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cavss.pipe.util.datatype.TypeConverting

@Entity(tableName = "UserDTO")
@TypeConverters(TypeConverting::class)
data class UserDTO (
    @ColumnInfo(name = "email")
    val email : HashMap<String, ByteArray> = hashMapOf(),

    @ColumnInfo(name = "pw")
    val pw : HashMap<String, ByteArray> = hashMapOf(),

    @ColumnInfo(name = "autoLogin")
    val autoLogin : Boolean,

    @ColumnInfo(name = "colourTheme")
    val colourTheme : HashMap<String, ByteArray> = hashMapOf(),

    @ColumnInfo(name = "userUID")
    val userUID : HashMap<String, ByteArray> = hashMapOf()
){
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0

    override fun toString() : String{
        return " id : ${id}\n email : ${email}\n pw : ${pw}\n autoLogin : ${autoLogin}\n colourTheme : ${colourTheme}\n userUID : ${userUID}"
    }
}