package com.vs.sample.transparentaccounts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vs.sample.transparentaccounts.databinding.ItemAccountBinding
import com.vs.sample.transparentaccounts.databinding.ItemTransactionBinding
import com.vs.sample.transparentaccounts.fragments.FragmentAccountsDirections
import com.vs.sample.transparentaccounts.fragments.FragmentTransactionsDirections
import com.vs.sample.transparentaccounts.models.Account
import com.vs.sample.transparentaccounts.models.Transaction

class AdapterTransactions: ListAdapter<Transaction, AdapterTransactions.TransactionsViewHolder>(TransactionDiffCallback())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionsViewHolder {
        return TransactionsViewHolder(ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TransactionsViewHolder, position: Int) {
        holder.binding.transaction = getItem(position)
        holder.binding.executePendingBindings()
    }

    class TransactionsViewHolder(internal val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.clickListener = View.OnClickListener {
                binding.transaction?.let {transaction ->
                    val directions = FragmentTransactionsDirections.actionFragmentTransactionsToFragmentTransactionDetail(transaction)
                    it.findNavController().navigate(directions)
                }
            }
        }
    }
}

private class TransactionDiffCallback : DiffUtil.ItemCallback<Transaction>() {

    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.processingDate == newItem.processingDate && oldItem.dueDate == newItem.dueDate
                && oldItem.sender == newItem.sender && oldItem.receiver == oldItem.receiver
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == newItem
    }
}