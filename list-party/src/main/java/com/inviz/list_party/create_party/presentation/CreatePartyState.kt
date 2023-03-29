package com.inviz.list_party.create_party.presentation

sealed class CreatePartyState {

    object Complete: CreatePartyState()

    data class Error(val e: Throwable): CreatePartyState()
}
