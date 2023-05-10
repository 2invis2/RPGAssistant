package com.inviz.list_party.list_party.presentation

import com.inviz.domain.entity.Party

sealed class ListPartyState {

    object InProgress : ListPartyState()

    data class Complete(val parties: Set<Party>?) : ListPartyState()

    data class Error(val throwable: Throwable) : ListPartyState()
}
