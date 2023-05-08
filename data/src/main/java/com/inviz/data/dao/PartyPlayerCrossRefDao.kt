package com.inviz.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.inviz.data.entity.PartyPlayerCrossRef

@Dao
interface PartyPlayerCrossRefDao {

    @Insert
    fun insert(partyPlayerCrossRef: PartyPlayerCrossRef)

    @Update
    fun update(partyPlayerCrossRef: PartyPlayerCrossRef)

    @Delete
    fun delete(partyPlayerCrossRef: PartyPlayerCrossRef)
}