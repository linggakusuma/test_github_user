package com.test.githubuser.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.test.githubuser.core.domain.model.User
import com.test.githubuser.core.domain.usecase.GithubUseCase
import com.test.githubuser.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class UserViewModel(private val useCase: GithubUseCase) : BaseViewModel() {

    private val _users = MutableLiveData<PagingData<User>>()
    val users : LiveData<PagingData<User>> get() = _users


     fun getUser(username:String){
        viewModelScope.launch {
            useCase.getGithubUser(username).cachedIn(viewModelScope).onStart {
                setLoading()
            }
                .collect {
                    _users.postValue(it)
                    finishLoading()
                }
        }
    }
}