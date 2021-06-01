package com.test.githubuser.ui.home

import android.os.Bundle
import android.view.View
import com.test.githubuser.R
import com.test.githubuser.databinding.FragmentHomeBinding
import com.test.githubuser.ui.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            appbar.iconSearch.setOnClickListener { navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment()) }
            appbar.backButton.visibility = View.GONE
        }
    }
}