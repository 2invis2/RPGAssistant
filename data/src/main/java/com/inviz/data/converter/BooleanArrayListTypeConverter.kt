package com.inviz.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BooleanArrayListTypeConverter {

    @TypeConverter
    fun fromString(value: String?): ArrayList<Boolean>? {
        if (value == null) {
            return null
        }
        val listType = object : TypeToken<ArrayList<Boolean>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: ArrayList<Boolean>?): String? {
        if (list == null) {
            return null
        }
        return Gson().toJson(list)
    }
}