package com.vs.sample.transparentaccounts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vs.sample.transparentaccounts.databinding.ItemTransactionBinding
import com.vs.sample.transparentaccounts.fragments.FragmentTransactionsDirections
import com.vs.sample.transparentaccounts.models.Transaction

class AdapterTransactionsPaged: PagedListAdapter<Transaction, AdapterTransactionsPaged.TransactionPagedViewHolder>(PagedTransactionDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionPagedViewHolder {
        return TransactionPagedViewHolder(ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TransactionPagedViewHolder, position: Int) {
        holder.binding.transaction = getItem(position)
        holder.binding.executePendingBindings()
    }


    class TransactionPagedViewHolder(internal val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { v ->
                binding.transaction?.let {
                    val action = FragmentTransactionsDirections.actionFragmentTransactionsToFragmentTransactionDetail(it)
                    v.findNavController().navigate(action)
                }
            }
        }
    }
}

private class PagedTransactionDiffCallback : DiffUtil.ItemCallback<Transaction>() {

    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.processingDate == newItem.processingDate && oldItem.amount == newItem.amount && oldItem.sender == newItem.sender
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == newItem
    }
}