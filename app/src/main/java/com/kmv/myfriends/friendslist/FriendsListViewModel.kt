package com.kmv.myfriends.friendslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmv.myfriends.friendslist.dataclasses.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FriendsListViewModel private constructor(
    private val repository: FriendsListRepository
) : ViewModel() {
    constructor() : this(FriendsListRepository())

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _friends = MutableStateFlow<List<Result>>(emptyList())
    val friends = _friends.asStateFlow()

    init {
        loadFriends()
    }

    private fun loadFriends() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.value = true
                repository.getFriends(100)
            }.fold(
                onSuccess = { _friends.value = it },
                onFailure = { Log.d("FriendsListViewModel", it.message ?: "") }
            )
            _isLoading.value = false
        }
    }
}