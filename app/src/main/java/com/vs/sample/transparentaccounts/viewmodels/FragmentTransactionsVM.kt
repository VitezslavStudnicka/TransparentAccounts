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

class FragmentTransactionsVM internal constructor(private val repo: Repository): ViewModel() {
    private val _refreshing = MutableLiveData<Boolean>(false)
    private val _pagedTransactions = initializedPagedListBuilder(getConfig()).build()

    var accountId: String? = null

    val refreshing : LiveData<Boolean>
        get() = _refreshing

    val pagedTransactions: LiveData<PagedList<Transaction>>
        get() = _pagedTransactions

    fun setRefreshing(value: Boolean) {
        _refreshing.value = value
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
