package com.inviz.domain.entity.char_sheet

import com.inviz.domain.entity.Player
import com.inviz.domain.entity.RPGSystem.*

data class CSFateCore(
    override val id: String,
    override val gameId: String?,
    override val player: Player,
    override val nameCharacter: String,
    val descriptionChar: String?,
    val concept: String,
    val trouble: String,
    val firstAspect: String,
    val secondAspect: String,
    val thirdAspect: String,
    val refresh: Byte,
    val skills: Map<Skills, Byte>,
    val stunts: String,
    val physicalStress: ArrayList<Boolean>,
    val mentalStress: ArrayList<Boolean>,
    val consequences: Map<Consequences, String>?,
    val extras: String?,
) : CharSheet(id, FATE_CORE, gameId, player, nameCharacter)

enum class Skills {
    ATHLETICS,
    BURGLARY,
    CONTACTS,
    CRAFTS,
    DECEIVE,
    DRIVE,
    EMPATHY,
    FIGHT,
    INVESTIGATE,
    LORE,
    NOTICE,
    PHYSIQUE,
    PROVOKE,
    RAPPORT,
    RESOURCES,
    SHOOT,
    STEALTH,
    WILL,
}

enum class Consequences {
    MILD,
    MODERATE,
    SEVERE,
    EXTRA_MILD,
}