package com.inviz.domain.use_case.party

import com.inviz.domain.PartyRepository
import com.inviz.domain.entity.Party

class SavePartyUseCase(private val repository: PartyRepository) {

    fun saveParty(party: Party){
        repository.insertParty(party)
    }
}