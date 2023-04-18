package com.inviz.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.inviz.domain.entity.PartyState
import com.inviz.domain.entity.RPGSystem

@Entity(
    indices = [Index("party_id")],
)
data class Party(
    @PrimaryKey
    @ColumnInfo(name = "party_id")
    val id: String,
    val name: String,
    val system: RPGSystem,
    @ColumnInfo(name = "gm_id")
    val gmId: String,
    val state: PartyState
)