package com.inviz.list_party.create_party.presentation

sealed class CreatePartyState {

    object Default: CreatePartyState()
    object InProgress: CreatePartyState()
    object Complete: CreatePartyState()

    data class Error(val e: Throwable): CreatePartyState()
}
