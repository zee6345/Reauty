package com.app.blingy.reauty.feature.search.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.blingy.reauty.R
import com.app.blingy.reauty.databinding.FragmentAccountsBinding
import com.app.blingy.reauty.feature.search.presentation.adapter.ItemAccountAdapter
import com.app.blingy.reauty.feature.search.presentation.contract.AccountContract
import com.app.blingy.reauty.feature.search.presentation.viewmodel.AccountsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class AccountsFragment : Fragment() {

    private var _binding: FragmentAccountsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AccountsViewModel by viewModels()
    private lateinit var accountAdapter: ItemAccountAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAccountsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupUi()
        observeUiState()
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.menu_search, menu)
//        val searchItem = menu.findItem(R.id.action_search)
//        val searchView = searchItem.actionView as SearchView
//        searchQueryListener(searchView)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.rvAccounts?.adapter = null
        _binding = null
    }

    private fun setupUi() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        accountAdapter = createAdapter()
        val accountLayoutManager = LinearLayoutManager(requireContext())
        binding.rvAccounts.apply {
            adapter = accountAdapter
            layoutManager = accountLayoutManager
            setHasFixedSize(true)
        }
        sentGetAllAccountsEvent()
    }

    private fun createAdapter() = ItemAccountAdapter() {
        // to handle click event
    }

    private fun sentGetAllAccountsEvent() {
        lifecycleScope.launch {
            viewModel.setEvent(
                AccountContract.AccountEvent.GetAllAccounts
            )
        }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state.isLoading) {
                    true -> binding.progress.isVisible = true
                    false -> binding.progress.isVisible = false
                }
                if (state.accounts.isNotEmpty()) {
                    Timber.d("getAllAccounts recycler view, ${state.accounts}")
                    accountAdapter.submitList(state.accounts)
                }
            }
        }
    }

    private fun searchQueryListener(searchView: SearchView){
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){

                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

}