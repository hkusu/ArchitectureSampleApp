package io.github.hkusu.architecturesampleapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.hkusu.architecturesampleapp.model.User
import io.github.hkusu.architecturesampleapp.model.usecase.GetUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getUser: GetUserUseCase): ViewModel() {

    private val _userList: MutableLiveData<List<User>> = MutableLiveData()
    val userList = _userList as LiveData<List<User>>

    init {
        viewModelScope.launch {
            _userList.value = getUser()
        }
    }
}
