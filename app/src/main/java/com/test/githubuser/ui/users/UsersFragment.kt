package com.test.githubuser.ui.users

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.githubuser.R
import com.test.githubuser.databinding.FragmentUsersBinding
import com.test.githubuser.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel


class UsersFragment : BaseFragment<FragmentUsersBinding>(R.layout.fragment_users) {

    private val args: UsersFragmentArgs by navArgs()

    private val userViewModel: UserViewModel by viewModel()

    private val adapter by lazy { UserAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = this@UsersFragment.adapter
            }
            appbar.backButton.setOnClickListener { findNavController().navigateUp() }
            appbar.iconSearch.setOnClickListener { navigate(UsersFragmentDirections.actionUsersFragmentToSearchFragment()) }
        }
    }

    private fun initObserver() {
        userViewModel.getUser(args.query.toString())
        userViewModel.users.observe(viewLifecycleOwner, {
            adapter.submitData(lifecycle, it)
        })
        userViewModel.getLoading().observe(viewLifecycleOwner, {
            if (it) binding.loading.visibility = View.VISIBLE
            else binding.loading.visibility = View.GONE
        })
    }
}