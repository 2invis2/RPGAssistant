package com.inviz.list_party.list_party.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inviz.base.presentation.BaseViewModel
import com.inviz.domain.entity.Party
import com.inviz.domain.entity.Player
import com.inviz.domain.use_case.party.LoadUsersPartiesUseCase
import com.inviz.list_party.list_party.presentation.ListPartyState.Complete
import com.inviz.list_party.list_party.presentation.ListPartyState.Error
import com.inviz.list_party.list_party.presentation.ListPartyState.InProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListPartyViewModel(
    private val loadUsersPartiesUseCase: LoadUsersPartiesUseCase,
) : BaseViewModel() {

    private val _state = MutableLiveData<ListPartyState>()
    val state: LiveData<ListPartyState> = _state
    private val user = Player(
        id = "000000",
        nickname = "invizTer",
        firstName = null,
        secondName = null,
        lastName = null,
    )

    fun loadParties() {
        _state.value = InProgress
        viewModelScope.launch {
            var parties: Set<Party>? = null
            try {
                withContext(Dispatchers.IO) {
                    parties = loadUsersPartiesUseCase.loadParties(user.id)
                }
            } catch (e: Exception) {
                handleError(e)
            } finally {
                _state.value = Complete(parties)
            }
        }
    }

    override fun handleError(throwable: Throwable) {
        _state.value = Error(throwable)
    }
}