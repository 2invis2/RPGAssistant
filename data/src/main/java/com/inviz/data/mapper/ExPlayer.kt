package com.inviz.domain.entity

import com.inviz.data.entity.Player

fun com.inviz.domain.entity.Player.mapToDataPlayer(): Player =
    Player(
        id = id,
        nickname = nickname,
        firstName = firstName,
        secondName = secondName,
        lastName = lastName,
    )

fun Player.mapToDomainPlayer(): com.inviz.domain.entity.Player =
    com.inviz.domain.entity.Player(
        id = id,
        nickname = nickname,
        firstName = firstName,
        secondName = secondName,
        lastName = lastName,
    )

fun List<Player>.mapToListDomainPlayer(): List<com.inviz.domain.entity.Player> =
    this.map { it.mapToDomainPlayer() }