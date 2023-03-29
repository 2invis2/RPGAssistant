package com.inviz.list_party.create_party.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inviz.domain.entity.Party
import com.inviz.domain.entity.Player
import com.inviz.domain.entity.RPGSystem

class CreatePartyViewModel : ViewModel() {

    private val _state = MutableLiveData<CreatePartyState>()
    val state: LiveData<CreatePartyState> = _state

    init {
        _state.value = CreatePartyState.Complete
    }

    fun createParty(system: RPGSystem, nameParty: String) {
        //TODO заглушка, потом убрать
        val gameMaster = Player(
            id = "000000",
            nickname = "invizTer",
            firstName = null,
            secondName = null,
            lastName = null,
            charSheetList = null,
        )

        val party = Party(
            id = null,
            name = nameParty,
            system = system,
            gameMaster = gameMaster,
            players = emptyList(),
        )
    }
}