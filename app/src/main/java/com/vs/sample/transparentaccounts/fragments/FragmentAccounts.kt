package com.vs.sample.transparentaccounts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vs.sample.transparentaccounts.adapters.AdapterAccountsPaged
import com.vs.sample.transparentaccounts.databinding.FragmentAccountsBinding
import com.vs.sample.transparentaccounts.utils.InjectorUtils
import com.vs.sample.transparentaccounts.viewmodels.FragmentAccountsVM

class FragmentAccounts : Fragment() {

    private val viewModel: FragmentAccountsVM by viewModels {
        InjectorUtils.provideAccountsVMFactory()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentAccountsBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.viewModel = viewModel
//        val adapter = AdapterAccounts()
        val adapter = AdapterAccountsPaged()
        binding.rvAccounts.adapter = adapter
        binding.rvAccounts.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.stopRefreshing()

        }
        /*viewModel.accounts.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }*/
        viewModel.pagedAccounts.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }
        viewModel.refreshing.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }

        return binding.root
    }
}
