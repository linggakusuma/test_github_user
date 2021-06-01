package com.test.githubuser.di

import com.test.githubuser.core.domain.usecase.GithubInteractor
import com.test.githubuser.core.domain.usecase.GithubUseCase
import com.test.githubuser.ui.search.SearchViewModel
import com.test.githubuser.ui.users.UserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GithubUseCase> { GithubInteractor(get()) }
}

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}