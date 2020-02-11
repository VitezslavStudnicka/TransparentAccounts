package com.vs.sample.transparentaccounts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vs.sample.transparentaccounts.databinding.ItemAccountBinding
import com.vs.sample.transparentaccounts.fragments.FragmentAccountsDirections
import com.vs.sample.transparentaccounts.models.Account

class AdapterAccounts: ListAdapter<Account, AdapterAccounts.AccountsViewHolder>(AccountDiffCallback())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsViewHolder {
        return AccountsViewHolder(ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AccountsViewHolder, position: Int) {
        holder.binding.account = getItem(position)
        holder.binding.executePendingBindings()
    }

    class AccountsViewHolder(internal val binding: ItemAccountBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.clickListener = View.OnClickListener {
                binding.account?.let {account ->
                    val directions = FragmentAccountsDirections.actionFragmentAccountsToFragmentTransactions(account.accountNumber)
                    it.findNavController().navigate(directions)
                }

            }
        }
    }
}

private class AccountDiffCallback : DiffUtil.ItemCallback<Account>() {

    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem.accountNumber == newItem.accountNumber
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem == newItem
    }
}