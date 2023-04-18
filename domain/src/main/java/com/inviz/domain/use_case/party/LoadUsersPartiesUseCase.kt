package com.inviz.domain.use_case.party

import com.inviz.domain.PartyRepository
import com.inviz.domain.entity.Party

class LoadUsersPartiesUseCase(private val repository: PartyRepository) {

    fun loadParties(userId: String): Set<Party>? {
        return repository.getPartiesByUserId(userId)
    }
}