package com.inviz.app.di

import android.content.Context
import com.inviz.domain.use_case.party.LoadUsersPartiesUseCase
import org.koin.dsl.module
import com.inviz.domain.use_case.party.SavePartyUseCase
import com.inviz.domain.use_case.player.SavePlayerUseCase

class DomainModule(private val context: Context) {
    val domainModule = module {
        single { SavePartyUseCase(get()) }
        single { LoadUsersPartiesUseCase(get()) }
        single { SavePlayerUseCase(get()) }
    }
}