package com.inviz.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.inviz.data.converter.BooleanArrayListTypeConverter
import com.inviz.data.converter.PartyStateConverter
import com.inviz.data.converter.RPGSystemConverter
import com.inviz.data.dao.CSFateCoreDao
import com.inviz.data.dao.PartyDao
import com.inviz.data.dao.PartyPlayerCrossRefDao
import com.inviz.data.dao.PlayerDao
import com.inviz.data.entity.Party
import com.inviz.data.entity.PartyPlayerCrossRef
import com.inviz.data.entity.Player
import com.inviz.data.entity.char_sheet.CSFateCore

@Database(
    entities = [
        Party::class,
        Player::class,
        CSFateCore::class,
        PartyPlayerCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    RPGSystemConverter::class,
    PartyStateConverter::class,
    BooleanArrayListTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun partyDao(): PartyDao
    abstract fun playerDao(): PlayerDao
    abstract fun csFateCoreDao(): CSFateCoreDao
    abstract fun partyPlayerCrossRefDao(): PartyPlayerCrossRefDao

    companion object {
        private const val DATABASE_NAME = "myApp.db"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).createFromAsset("db.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}