package com.vs.sample.transparentaccounts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vs.sample.transparentaccounts.adapters.AdapterTransactionsPaged
import com.vs.sample.transparentaccounts.databinding.FragmentTransactionsBinding
import com.vs.sample.transparentaccounts.utils.InjectorUtils
import com.vs.sample.transparentaccounts.viewmodels.FragmentTransactionsVM

class FragmentTransactions : Fragment() {

    private val viewModel: FragmentTransactionsVM by viewModels {
        InjectorUtils.provideTransactionsVMFactory()
    }

    private val args : FragmentTransactionsArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        context ?: return binding.root

        viewModel.accountId = args.account.accountNumber
        binding.account = args.account
        binding.cvAccountInfo.setOnClickListener {
            val action = FragmentTransactionsDirections.actionFragmentTransactionsToFragmentAccountDetail(args.account)
            findNavController().navigate(action)
        }

        val adapter = AdapterTransactionsPaged()
        binding.rvTransactions.adapter = adapter
        binding.rvTransactions.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        viewModel.pagedTransactions.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }
        viewModel.refreshing.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.setRefreshing(false)
            viewModel.reloadData()
        }

        return binding.root
    }

}
