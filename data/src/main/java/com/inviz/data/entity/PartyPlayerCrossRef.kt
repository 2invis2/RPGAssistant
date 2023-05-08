package com.inviz.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["party_id", "player_id"])
data class PartyPlayerCrossRef(

    @ColumnInfo(name = "party_id")
    val partyId: String,

    @ColumnInfo(name = "player_id")
    val playerId: String
)