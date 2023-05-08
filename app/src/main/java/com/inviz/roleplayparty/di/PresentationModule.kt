package com.inviz.roleplayparty.di

import android.content.Context
import com.inviz.list_party.create_party.presentation.CreatePartyViewModel
import com.inviz.list_party.list_party.presentation.ListPartyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class PresentationModule(private val context: Context) {
    val presentationModule = module{
        viewModel{ CreatePartyViewModel(get()) }
        viewModel{ ListPartyViewModel(get()) }
    }
}