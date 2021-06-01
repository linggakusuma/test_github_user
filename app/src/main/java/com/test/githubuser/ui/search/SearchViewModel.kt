package com.test.githubuser.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.githubuser.core.data.source.local.entity.SearchEntity
import com.test.githubuser.core.domain.model.Search
import com.test.githubuser.core.domain.usecase.GithubUseCase
import com.test.githubuser.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(private val useCase: GithubUseCase) : BaseViewModel() {

    private val _keywords = MutableLiveData<List<Search>>()
    val keywords: LiveData<List<Search>> get() = _keywords

    init {
        getKeyword()
    }

    fun saveKeyword(searchEntity: SearchEntity) = useCase.saveKeyword(searchEntity)

    private fun getKeyword() {
        viewModelScope.launch {
            useCase.getKeyword().collect {
                _keywords.postValue(it)
            }
        }
    }

    fun deleteKeyword(search: Search) = useCase.deleteKeyword(search)
}