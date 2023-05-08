package com.inviz.data.converter

import androidx.room.TypeConverter
import com.inviz.domain.entity.PartyState

class CSFateCoreEnumConverter {

    @TypeConverter
    fun fromPartyState(value: PartyState): String {
        return value.name
    }

    @TypeConverter
    fun toPartyState(value: String): PartyState {
        return PartyState.valueOf(value)
    }
}