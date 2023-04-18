package com.inviz.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PartyPlayerCrossRef(

    @ColumnInfo(name = "party_id")
    val partyId: String,

    @PrimaryKey
    @ColumnInfo(name = "player_id")
    val playerId: String
)