package com.inviz.data.repository

import com.inviz.domain.PartyRepository
import com.inviz.data.dao.PartyDao
import com.inviz.data.dao.PartyPlayerCrossRefDao
import com.inviz.data.entity.toDataParty
import com.inviz.data.entity.toDataPartyPlayerCrossRef
import com.inviz.data.entity.toListDomainParties
import com.inviz.domain.entity.Party

class PartyRepositoryImpl(
    private val partyDao: PartyDao,
    private val partyPlayerCrossRefDao: PartyPlayerCrossRefDao,
) : PartyRepository {

    override fun getPartiesByUserId(userId: String): Set<Party>? {
        return partyDao.getPartiesByUserId(userId).toListDomainParties()
    }

    override fun insertParty(party: Party) {
        partyDao.insert(party.toDataParty())
        partyPlayerCrossRefDao.insert(party.toDataPartyPlayerCrossRef())
    }

    override fun deleteParty(party: Party) {
    }

    override fun updateParty(party: Party) {
    }

}