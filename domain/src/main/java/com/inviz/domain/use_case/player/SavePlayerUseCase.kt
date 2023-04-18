package com.inviz.domain.use_case.player

import com.inviz.domain.PlayerRepository
import com.inviz.domain.entity.Player

class SavePlayerUseCase(private val playerRepository: PlayerRepository) {

    suspend fun savePlayer(player: Player){
        playerRepository.insertPlayer(player)
    }
}