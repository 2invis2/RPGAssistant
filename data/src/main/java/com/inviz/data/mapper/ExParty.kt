package com.inviz.data.entity

import com.inviz.data.pojo.PartyWithPlayersAndCSFC
import com.inviz.domain.entity.Party
import com.inviz.domain.entity.mapToDomainPlayer

fun List<PartyWithPlayersAndCSFC>?.toListDomainParties(): Set<Party>? =
    this?.map {
        it.let { party ->
            val playersAndCS = this.mapToPairListsDomainPlayersAndCS(party)
            Party(
                id = party.party.id,
                name = party.party.name,
                system = party.party.system,
                gameMaster = party.gameMaster.mapToDomainPlayer(),
                state = party.party.state,
                players = playersAndCS?.first,
                charSheets = playersAndCS?.second,
            )
        }
    }?.toSet()

private fun List<PartyWithPlayersAndCSFC>?.mapToPairListsDomainPlayersAndCS(party: PartyWithPlayersAndCSFC)
        : Pair<Set<com.inviz.domain.entity.Player>,
        Set<com.inviz.domain.entity.char_sheet.CSFateCore>>? {

    return this?.filter { it.party.id == party.party.id }
        ?.partition { it.player != null }
        ?.let { (players, _) ->

            val playerSet = players.mapNotNull {
                it.player?.mapToDomainPlayer()
            }.toSet()

            val csFateCoreSet = players.mapNotNull { partyPlayer ->
                partyPlayer.characterSheets?.toDomainCSFateCore(partyPlayer.player!!)
            }.toSet()

            return@let Pair(playerSet, csFateCoreSet)
        }
}

fun Party.toDataParty(): com.inviz.data.entity.Party =
    Party(
        id = id,
        name = name,
        system = system,
        gmId = gameMaster.id,
        state = state
    )

fun Party.toDataPartyPlayerCrossRef(): PartyPlayerCrossRef =
    PartyPlayerCrossRef(
        partyId = this.id,
        playerId = this.gameMaster.id
    )
