package com.inviz.data.entity

import com.inviz.data.entity.char_sheet.CSFateCore
import com.inviz.domain.entity.char_sheet.Consequences
import com.inviz.domain.entity.char_sheet.Skills
import com.inviz.domain.entity.mapToDomainPlayer

fun CSFateCore.toDomainCSFateCore(player: Player): com.inviz.domain.entity.char_sheet.CSFateCore {
    val skillsMap = mapOf(
        Skills.ATHLETICS to athletics,
        Skills.BURGLARY to burglary,
        Skills.CONTACTS to contacts,
        Skills.CRAFTS to crafts,
        Skills.DECEIVE to deceive,
        Skills.DRIVE to drive,
        Skills.EMPATHY to empathy,
        Skills.FIGHT to fight,
        Skills.INVESTIGATE to investigate,
        Skills.LORE to lore,
        Skills.NOTICE to notice,
        Skills.PHYSIQUE to physique,
        Skills.PROVOKE to provoke,
        Skills.RAPPORT to rapport,
        Skills.RESOURCES to resources,
        Skills.SHOOT to shoot,
        Skills.STEALTH to stealth,
        Skills.WILL to will
    )

    val consequencesMap =
        if (mild.isNotBlank()
            || moderate.isNotBlank()
            || severe.isNotBlank()
            || extraMild.isNotBlank()
        ) {
            mapOf(
                Consequences.MILD to mild,
                Consequences.MODERATE to moderate,
                Consequences.SEVERE to severe,
                Consequences.EXTRA_MILD to extraMild
            )
        } else {
            null
        }

    return com.inviz.domain.entity.char_sheet.CSFateCore(
        id = id,
        gameId = partyId,
        player = player.mapToDomainPlayer(),
        nameCharacter = nameCharacter,
        descriptionChar = descriptionCharacter,
        concept = concept,
        trouble = trouble,
        firstAspect = firstAspect,
        secondAspect = secondAspect,
        thirdAspect = thirdAspect,
        refresh = refresh,
        skills = skillsMap,
        stunts = stunts,
        physicalStress = physicalStress,
        mentalStress = mentalStress,
        consequences = consequencesMap,
        extras = extras
    )
}

fun List<CSFateCore>.toDomainListCSFateCore(players: List<Player>)
        : List<com.inviz.domain.entity.char_sheet.CSFateCore> =
    this.map {
        it.findPlayerInList(players).let { player ->
            it.toDomainCSFateCore(player)
        }
    }

fun CSFateCore.findPlayerInList(players: List<Player>): Player =
    players.first { it.id == this.playerId }