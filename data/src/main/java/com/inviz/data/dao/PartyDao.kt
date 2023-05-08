package com.inviz.data.dao

import androidx.room.*
import com.inviz.data.entity.Party
import com.inviz.data.pojo.PartyWithPlayersAndCSFC

@Dao
interface PartyDao {

    @Transaction
    @Query(
        "SELECT Party.*, " +
                "gm.player_id AS gm_player_id, gm.nickname AS gm_nickname, " +
                "gm.first_name AS gm_first_name, gm.second_name AS gm_second_name, gm.last_name AS gm_last_name, " +
                "Player.*, " +
                "csfc.* " +
                "FROM Party " +
                "JOIN PartyPlayerCrossRef ppcf ON Party.party_id = ppcf.party_id " +
                "LEFT JOIN Player gm ON Party.gm_id = gm.player_id " +
                "LEFT JOIN Player ON Player.player_id = ppcf.player_id AND Party.gm_id != Player.player_id " +
                "LEFT JOIN CSFateCore csfc ON csfc.attached_player_id = Player.player_id " +
                "WHERE ppcf.player_id = :userId"
    )
    fun getPartiesByUserId(userId: String): List<PartyWithPlayersAndCSFC>?

    @Insert
    fun insert(party: Party)

    @Update
    fun update(party: Party)

    @Delete
    fun delete(party: Party)
}