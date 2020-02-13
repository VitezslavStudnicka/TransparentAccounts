package com.vs.sample.transparentaccounts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vs.sample.transparentaccounts.databinding.ItemAccountBinding
import com.vs.sample.transparentaccounts.fragments.FragmentAccountsDirections
import com.vs.sample.transparentaccounts.models.Account

class AdapterAccountsPaged: PagedListAdapter<Account, AdapterAccountsPaged.AccountsPagedViewHolder>(PagedAccountDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsPagedViewHolder {
        return AccountsPagedViewHolder(ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AccountsPagedViewHolder, position: Int) {
        holder.binding.account = getItem(position)
        holder.binding.executePendingBindings()
    }


    class AccountsPagedViewHolder(internal val binding: ItemAccountBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { v ->
                binding.account?.let {
                    val action = FragmentAccountsDirections.actionFragmentAccountsToFragmentTransactions(it)
                    v.findNavController().navigate(action)
                }
            }
        }
    }
}

private class PagedAccountDiffCallback : DiffUtil.ItemCallback<Account>() {

    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem.accountNumber == newItem.accountNumber
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem == newItem
    }
}