package com.vs.sample.transparentaccounts.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vs.sample.transparentaccounts.models.Transaction
import com.vs.sample.transparentaccounts.repository.Repository
import kotlinx.coroutines.launch

class FragmentTransactionsVM internal constructor(private val repo: Repository): ViewModel() {
    private val _transactions = MutableLiveData<List<Transaction>>(ArrayList())
    private val _refreshing = MutableLiveData<Boolean>(false)

    val transactions : LiveData<List<Transaction>>
        get() = _transactions

    val refreshing : LiveData<Boolean>
        get() = _refreshing

    fun stopRefreshing() {
        _refreshing.value = false
    }

    fun getTransactions(accountId: String) {
        viewModelScope.launch {
            _refreshing.value = true
            repo.getTransactions(accountId)?.let {
                it.transactions?.let {transactions ->
                    _transactions.value = transactions
                }
            }
            _refreshing.value = false
        }
    }

}
