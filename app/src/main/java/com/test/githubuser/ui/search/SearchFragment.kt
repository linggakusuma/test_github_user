package com.test.githubuser.ui.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.githubuser.R
import com.test.githubuser.core.data.source.local.entity.SearchEntity
import com.test.githubuser.core.domain.model.Search
import com.test.githubuser.databinding.FragmentSearchBinding
import com.test.githubuser.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val searchViewModel: SearchViewModel by viewModel()

    private val adapter by lazy { SearchAdapter { searchFromSaveKeyword(it) } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        binding.apply {
            appbar.backButton.setOnClickListener { findNavController().navigateUp() }
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = this@SearchFragment.adapter
            }
            searchUser(this)
        }
    }

    private fun initObserver() {
        searchViewModel.keywords.observe(viewLifecycleOwner, {
            adapter.submitData(it)
            if (it.size > 10) searchViewModel.deleteKeyword(it[0])
        })
    }

    private fun searchUser(binding: FragmentSearchBinding) {
        binding.appbar.searchView.apply {
            requestFocusFromTouch()
            isFocusable = true
            setOnQueryTextFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    showInputMethod(view.findFocus())
                }
            }

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchViewModel.saveKeyword(SearchEntity(keyword = query))
                    navigate(SearchFragmentDirections.actionSearchFragmentToUsersFragment(query))
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean = false
            })
        }
    }

    private fun searchFromSaveKeyword(search: Search) {
        navigate(SearchFragmentDirections.actionSearchFragmentToUsersFragment(search.keyword))
    }

    private fun showInputMethod(view: View) {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(view, 0)
    }
}