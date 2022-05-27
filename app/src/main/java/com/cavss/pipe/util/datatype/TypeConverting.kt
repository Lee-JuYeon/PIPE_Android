package com.cavss.pipe.util.datatype

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


object TypeConverting {

    @TypeConverter
    @JvmStatic
    fun hashMapToString(map: HashMap<String, ByteArray>): String {
        return Gson().toJson(map)
    }

    @TypeConverter
    @JvmStatic
    fun stringToHashMap(value: String): HashMap<String, ByteArray> {
        val type = object : TypeToken<HashMap<String, ByteArray>>() {}.type
        return Gson().fromJson(value, type)
    }


    @TypeConverter
    @JvmStatic
    fun listToString(value: ArrayList<String>) : String {
        val type = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    @JvmStatic
    fun stringToList(value: String) : ArrayList<String>{
        val type = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, type)
    }
}