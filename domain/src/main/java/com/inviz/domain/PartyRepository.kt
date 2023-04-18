package com.inviz.domain

import com.inviz.domain.entity.Party

interface PartyRepository {

    fun getPartiesByUserId(userId: String): Set<Party>?

    fun insertParty(party: Party)

    fun deleteParty(party: Party)

    fun updateParty(party: Party)
}