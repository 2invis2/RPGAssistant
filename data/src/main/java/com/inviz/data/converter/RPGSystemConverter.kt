package com.inviz.data.converter

import androidx.room.TypeConverter
import com.inviz.domain.entity.RPGSystem

class RPGSystemConverter {
    @TypeConverter
    fun fromRPGSystem(value: RPGSystem): String {
        return value.toString()
    }

    @TypeConverter
    fun toRPGSystem(value: String): RPGSystem {
        return RPGSystem.valueOf(value)
    }
}