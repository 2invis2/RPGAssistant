package com.inviz.roleplayparty.di

import com.inviz.domain.PartyRepository
import android.content.Context
import com.inviz.data.database.AppDatabase
import com.inviz.data.repository.PartyRepositoryImpl
import com.inviz.data.repository.PlayerRepositoryImpl
import com.inviz.domain.PlayerRepository
import org.koin.dsl.module

class DataModule(private val context: Context) {
    val dataModule = module {
        single { AppDatabase.getInstance(context) }
        single { get<AppDatabase>().partyDao() }
        single { get<AppDatabase>().playerDao() }
        single { get<AppDatabase>().csFateCoreDao() }
        single { get<AppDatabase>().partyPlayerCrossRefDao() }
        single<PartyRepository> { PartyRepositoryImpl(get(), get()) }
        single<PlayerRepository> { PlayerRepositoryImpl(get()) }
    }
}