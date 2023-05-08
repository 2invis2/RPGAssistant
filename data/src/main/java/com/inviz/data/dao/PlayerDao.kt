package com.inviz.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.inviz.data.entity.Player

@Dao
interface PlayerDao {

    @Insert
    fun insert(player: Player)

    @Update
    fun update(player: Player)

    @Delete
    fun delete(player: Player)
}