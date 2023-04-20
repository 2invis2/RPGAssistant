package com.inviz.list_party.create_party.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inviz.domain.entity.Party
import com.inviz.domain.entity.PartyState
import com.inviz.domain.entity.Player
import com.inviz.domain.entity.RPGSystem
import com.inviz.domain.use_case.party.SavePartyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class CreatePartyViewModel(
    private val savePartyUseCase: SavePartyUseCase
) : ViewModel() {

    private val _state = MutableLiveData<CreatePartyState>()
    val state: LiveData<CreatePartyState> = _state

    init {
        _state.value = CreatePartyState.Default
    }

    fun createParty(system: RPGSystem, nameParty: String) {
        //TODO заглушка, потом убрать
        val gameMaster = Player(
            id = "000000",
            nickname = "invizTer",
            firstName = null,
            secondName = null,
            lastName = null,
        )

        val party = Party(
            id = UUID.randomUUID().toString(),
            name = nameParty,
            system = system,
            gameMaster = gameMaster,
            state = PartyState.ACTIVE,
            players = emptySet(),
            charSheets = emptySet(),

        )

        saveParty(party)
    }

    private fun saveParty(party: Party) {
        _state.value = CreatePartyState.InProgress
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    savePartyUseCase.saveParty(party)
                }
            } catch (e: Exception) {

            }
            finally {
                _state.value = CreatePartyState.Complete
            }
        }
    }
}