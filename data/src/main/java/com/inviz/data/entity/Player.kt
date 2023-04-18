package com.inviz.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index("player_id")],
)
data class Player(
    @PrimaryKey
    @ColumnInfo(name = "player_id")
    val id: String,
    val nickname: String,
    @ColumnInfo(name = "first_name")
    val firstName: String?,
    @ColumnInfo(name = "second_name")
    val secondName: String?,
    @ColumnInfo(name = "last_name")
    val lastName: String?,
)