package com.inviz.list_party.create_party.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inviz.base.presentation.BaseViewModel
import com.inviz.domain.entity.Party
import com.inviz.domain.entity.PartyState
import com.inviz.domain.entity.Player
import com.inviz.domain.entity.RPGSystem
import com.inviz.domain.use_case.party.SavePartyUseCase
import com.inviz.list_party.create_party.presentation.CreatePartyState.Complete
import com.inviz.list_party.create_party.presentation.CreatePartyState.Default
import com.inviz.list_party.create_party.presentation.CreatePartyState.Error
import com.inviz.list_party.create_party.presentation.CreatePartyState.InProgress
import com.inviz.utility.SingleLiveEvent
import com.inviz.validator.StringsValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class CreatePartyViewModel(
    private val savePartyUseCase: SavePartyUseCase
) : BaseViewModel() {

    private val _state = MutableLiveData<CreatePartyState>()
    val state: LiveData<CreatePartyState> = _state

    private val _validateText = SingleLiveEvent<CreatePartyTextValidation>()
    val validateText: LiveData<CreatePartyTextValidation> = _validateText

    private val validator = StringsValidator()

    init {
        _state.value = Default
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
        _state.value = InProgress
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    savePartyUseCase.saveParty(party)
                }
            } catch (e: Exception) {
                handleError(e)
            } finally {
                _state.value = Complete
            }
        }
    }

    fun isValidText(nameParty: String, nameSystem: String) {
        val validText = CreatePartyTextValidation(
            validator.isNotBlankOrEmpty(nameSystem),
            validator.isNotBlankOrEmpty(nameParty)
        )
        _validateText.value = validText
    }

    override fun handleError(throwable: Throwable) {
        _state.value = Error(throwable)
    }
}