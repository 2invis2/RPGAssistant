package com.inviz.data.pojo

import androidx.room.Embedded
import com.inviz.data.entity.Party
import com.inviz.data.entity.Player
import com.inviz.data.entity.char_sheet.CSFateCore

data class PartyWithPlayersAndCSFC(
    @Embedded
    val party: Party,
    @Embedded(prefix = "gm_")
    val gameMaster: Player,
    @Embedded
    val player: Player?,
    @Embedded
    val characterSheets: CSFateCore?,
)