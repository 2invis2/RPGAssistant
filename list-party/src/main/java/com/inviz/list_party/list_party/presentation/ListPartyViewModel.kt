package com.inviz.list_party.list_party.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inviz.domain.entity.Party
import com.inviz.domain.entity.PartyState
import com.inviz.domain.entity.Player
import com.inviz.domain.entity.RPGSystem
import com.inviz.domain.use_case.party.LoadUsersPartiesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ListPartyViewModel(
    private val loadUsersPartiesUseCase: LoadUsersPartiesUseCase,
) : ViewModel() {

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

        _state.value = ListPartyState.InProgress
        viewModelScope.launch {
            var parties: Set<Party>? = null
            try {
                withContext(Dispatchers.IO) {
                    parties = loadUsersPartiesUseCase.loadParties(user.id)
                }
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка выполнения запроса: ${e.message}")
            } finally {
                _state.value = ListPartyState.Complete(parties)
            }
        }
    }
}