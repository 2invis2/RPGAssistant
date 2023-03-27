package com.inviz.domain.entity

import com.inviz.domain.entity.char_sheet.CharSheet

data class Player(
    val id: String,
    val nickname: String,
    val firstName: String?,
    val secondName: String?,
    val lastName: String?,
    val charSheetList: List<CharSheet>?,
)