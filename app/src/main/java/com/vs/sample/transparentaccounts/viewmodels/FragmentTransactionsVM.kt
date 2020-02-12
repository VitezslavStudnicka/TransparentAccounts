package com.vs.sample.transparentaccounts.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.vs.sample.transparentaccounts.adapters.DataSourceTransaction
import com.vs.sample.transparentaccounts.models.Transaction
import com.vs.sample.transparentaccounts.repository.Repository
import com.vs.sample.transparentaccounts.utils.Consts
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class FragmentTransactionsVM internal constructor(private val repo: Repository): ViewModel() {
    private val _transactions = MutableLiveData<List<Transaction>>(ArrayList())
    private val _refreshing = MutableLiveData<Boolean>(false)
    private val _pagedTransactions = initializedPagedListBuilder(getConfig()).build()

    var accountId: String? = null

    val transactions : LiveData<List<Transaction>>
        get() = _transactions

    val refreshing : LiveData<Boolean>
        get() = _refreshing

    val pagedTransactions: LiveData<PagedList<Transaction>>
        get() = _pagedTransactions


    fun stopRefreshing() {
        _refreshing.value = false
    }

    fun setRefreshing(value: Boolean) {
        _refreshing.value = value
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

    private fun getConfig(): PagedList.Config = PagedList.Config.Builder()
        .setPageSize(Consts.DEFAULT_PAGE_SIZE)
        .setEnablePlaceholders(false)
        .setPrefetchDistance(Consts.DEFAULT_PREFETCHSIZE_SIZE)
        .build()

    private fun initializedPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, Transaction> {

        val dataSourceFactory = object : DataSource.Factory<Int, Transaction>() {
            override fun create(): DataSource<Int, Transaction> {
                return DataSourceTransaction(viewModelScope,this@FragmentTransactionsVM )
            }
        }

        return LivePagedListBuilder<Int, Transaction>(dataSourceFactory, config)
    }

}
