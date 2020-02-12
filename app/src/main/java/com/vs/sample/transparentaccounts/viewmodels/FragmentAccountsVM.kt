package com.vs.sample.transparentaccounts.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.vs.sample.transparentaccounts.adapters.DataSourceAccount
import com.vs.sample.transparentaccounts.models.Account
import com.vs.sample.transparentaccounts.repository.Repository
import com.vs.sample.transparentaccounts.utils.Consts
import com.vs.sample.transparentaccounts.utils.notifyObserver
import com.vs.sample.transparentaccounts.utils.randomString
import kotlinx.coroutines.launch

class FragmentAccountsVM internal constructor(private val repo: Repository): ViewModel() {
    private val _accounts = MutableLiveData<List<Account>>(ArrayList())
    private val _refreshing = MutableLiveData<Boolean>(false)
    private val _pagedAccounts = initializedPagedListBuilder(getConfig()).build()


    val accounts : LiveData<List<Account>>
        get() = _accounts

    val refreshing : LiveData<Boolean>
        get() = _refreshing

    val pagedAccounts: LiveData<PagedList<Account>>
        get() = _pagedAccounts


    fun getAccounts() {
        viewModelScope.launch {
            _refreshing.value = true
            repo.getAccounts()?.let {
                it.accounts?.let {accounts ->
                    _accounts.value = accounts
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

    private fun initializedPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, Account> {

        val dataSourceFactory = object : DataSource.Factory<Int, Account>() {
            override fun create(): DataSource<Int, Account> {
                return DataSourceAccount(viewModelScope)
            }
        }

        return LivePagedListBuilder<Int, Account>(dataSourceFactory, config)
    }
}
