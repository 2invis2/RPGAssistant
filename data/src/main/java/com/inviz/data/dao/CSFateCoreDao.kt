package com.inviz.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.inviz.data.entity.char_sheet.CSFateCore

@Dao
interface CSFateCoreDao {

    @Insert
    fun insert(charSheet: CSFateCore)

    @Update
    fun update(charSheet: CSFateCore)

    @Delete
    fun delete(charSheet: CSFateCore)
}