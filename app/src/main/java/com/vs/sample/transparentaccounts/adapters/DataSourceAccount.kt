package com.vs.sample.transparentaccounts.adapters

import androidx.paging.PageKeyedDataSource
import com.vs.sample.transparentaccounts.models.Account
import com.vs.sample.transparentaccounts.utils.Consts
import com.vs.sample.transparentaccounts.utils.InjectorUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DataSourceAccount(private val coroutineScope: CoroutineScope, val refresh: (Boolean) -> Unit): PageKeyedDataSource<Int, Account>() {

    private val repository = InjectorUtils.provideRepository()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Account>) {
        coroutineScope.launch {
            refresh.invoke(true)
            repository.getAccounts(0, size = Consts.DEFAULT_PAGE_SIZE)?.let {
                it.accounts?.let { accounts ->
                    callback.onResult(accounts, -1, 1)
                }
            }
            refresh.invoke(false)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Account>) {
        coroutineScope.launch {
            refresh.invoke(true)
            repository.getAccounts(params.key, size =Consts.DEFAULT_PAGE_SIZE)?.let {
                it.accounts?.let {accounts ->
                    callback.onResult(accounts, params.key+1)
                }
            }
            refresh.invoke(false)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Account>) {

    }

}