package com.inviz.data.repository

import com.inviz.data.dao.PlayerDao
import com.inviz.domain.PlayerRepository
import com.inviz.domain.entity.Player
import com.inviz.domain.entity.mapToDataPlayer

class PlayerRepositoryImpl(private val playerDao: PlayerDao) : PlayerRepository {

    override fun insertPlayer(player: Player) {
        playerDao.insert(player.mapToDataPlayer())
    }

    override fun deletePlayer(player: Player) {
        playerDao.delete(player.mapToDataPlayer())
    }

    override fun updatePlayer(player: Player) {
        playerDao.update(player.mapToDataPlayer())
    }
}