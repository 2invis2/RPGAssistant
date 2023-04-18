package com.inviz.domain

import com.inviz.domain.entity.Player

interface PlayerRepository {

    fun insertPlayer(player: Player)

    fun deletePlayer(player: Player)

    fun updatePlayer(player: Player)
}