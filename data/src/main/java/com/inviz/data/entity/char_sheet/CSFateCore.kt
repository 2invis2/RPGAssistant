package com.inviz.data.entity.char_sheet

import androidx.room.*

@Entity(
    indices = [Index("csfc_id")],
)
data class CSFateCore(
    @PrimaryKey
    @ColumnInfo(name = "csfc_id")
    val id: String,
    @ColumnInfo(name = "attached_party_id")
    val partyId: String,
    @ColumnInfo(name = "attached_player_id")
    val playerId: String,
    @ColumnInfo(name = "name_character")
    val nameCharacter: String,
    @ColumnInfo(name = "description_character")
    val descriptionCharacter: String?,
    val concept: String,
    val trouble: String,
    @ColumnInfo(name = "first_aspect")
    val firstAspect: String,
    @ColumnInfo(name = "second_aspect")
    val secondAspect: String,
    @ColumnInfo(name = "third_aspect")
    val thirdAspect: String,
    val refresh: Byte,

    //Skills
    val athletics: Byte,
    val burglary: Byte,
    val contacts: Byte,
    val crafts: Byte,
    val deceive: Byte,
    val drive: Byte,
    val empathy: Byte,
    val fight: Byte,
    val investigate: Byte,
    val lore: Byte,
    val notice: Byte,
    val physique: Byte,
    val provoke: Byte,
    val rapport: Byte,
    val resources: Byte,
    val shoot: Byte,
    val stealth: Byte,
    val will: Byte,

    val stunts: String,
    @ColumnInfo(name = "physical_stress")
    val physicalStress: ArrayList<Boolean>,
    @ColumnInfo(name = "mental_stress")
    val mentalStress: ArrayList<Boolean>,

    //Consequences
    val mild: String,
    val moderate: String,
    val severe: String,
    @ColumnInfo(name = "extra_mild")
    val extraMild: String,

    val extras: String?,
)