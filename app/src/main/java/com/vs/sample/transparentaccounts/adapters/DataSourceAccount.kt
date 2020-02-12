package com.vs.sample.transparentaccounts.adapters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.vs.sample.transparentaccounts.models.Account
import com.vs.sample.transparentaccounts.models.NetworkState
import com.vs.sample.transparentaccounts.utils.InjectorUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DataSourceAccount(private val coroutineScope: CoroutineScope): PageKeyedDataSource<Int, Account>() {

    private val _networkState = MutableLiveData<NetworkState>(NetworkState.NOTLOADING)

    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val repository = InjectorUtils.provideRepository()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Account>) {

        coroutineScope.launch {
            _networkState.value = NetworkState.LOADING
            repository.getAccounts(0, size = params.requestedLoadSize)?.let {
                it.accounts?.let { accounts ->
                    callback.onResult(accounts, -1, 1)
                }
            }?: invalidate()
            _networkState.value = NetworkState.NOTLOADING
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Account>) {
        coroutineScope.launch {
            _networkState.value = NetworkState.LOADING
            repository.getAccounts(params.key, params.requestedLoadSize)?.let {
                it.accounts?.let {accounts ->
                    callback.onResult(accounts, params.key+1)
                }
            }?: invalidate()
            _networkState.value = NetworkState.NOTLOADING
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Account>) {
        coroutineScope.launch {
            _networkState.value = NetworkState.LOADING
            repository.getAccounts(params.key, params.requestedLoadSize)?.let {
                it.accounts?.let {accounts ->
                    callback.onResult(accounts, params.key-1)
                }
            }?: invalidate()
            _networkState.value = NetworkState.NOTLOADING
        }
    }

    override fun invalidate() {
        super.invalidate()
        coroutineScope.cancel()
    }
}