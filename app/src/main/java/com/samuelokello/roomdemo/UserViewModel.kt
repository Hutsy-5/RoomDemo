package com.samuelokello.roomdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userDao: UserDao) : ViewModel() {


    val users: Flow<List<User>> = userDao.getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addUser(user: User) {
        viewModelScope.launch {
            userDao.insertAll(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userDao.delete(user)
        }
    }
}