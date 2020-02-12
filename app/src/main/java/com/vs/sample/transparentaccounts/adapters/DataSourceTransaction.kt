package com.vs.sample.transparentaccounts.adapters

import androidx.paging.PageKeyedDataSource
import com.vs.sample.transparentaccounts.models.Transaction
import com.vs.sample.transparentaccounts.utils.Consts
import com.vs.sample.transparentaccounts.utils.InjectorUtils
import com.vs.sample.transparentaccounts.viewmodels.FragmentTransactionsVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class DataSourceTransaction(private val coroutineScope: CoroutineScope, private val viewModel: FragmentTransactionsVM): PageKeyedDataSource<Int, Transaction>() {

    private val repository = InjectorUtils.provideRepository()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Transaction>) {
        coroutineScope.launch {
            viewModel.setRefreshing(true)
            repository.getTransactions(viewModel.accountId!!, page = 0, size = Consts.DEFAULT_PAGE_SIZE)?.let {
                it.transactions?.let { transactions ->
                    callback.onResult(transactions, 0, 1)
                }
            }?: invalidate()
            viewModel.setRefreshing(false)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Transaction>) {
        coroutineScope.launch {
            viewModel.setRefreshing(true)
            repository.getTransactions(viewModel.accountId!!, page = params.key, size = Consts.DEFAULT_PAGE_SIZE)?.let {
                it.transactions?.let {transactions ->
                    callback.onResult(transactions, params.key+1)
                }
            }
            viewModel.setRefreshing(false)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Transaction>) {

    }

}

// TODO Convert and fix query by Date
/*class DataSourceTransaction(private val coroutineScope: CoroutineScope, private val viewModel: FragmentTransactionsVM): ItemKeyedDataSource<Date, Transaction>() {

    private val repository = InjectorUtils.provideRepository()

    override fun loadInitial(params: LoadInitialParams<Date>, callback: LoadInitialCallback<Transaction>) {
        coroutineScope.launch {
            repository.getTransactions(viewModel.accountId!!, size = Consts.DEFAULT_PAGE_SIZE)?.let {
                it.transactions?.let { transactions ->
                    callback.onResult(transactions)
                }
            }?: invalidate()
        }
    }

    override fun loadAfter(params: LoadParams<Date>, callback: LoadCallback<Transaction>) {
        coroutineScope.launch {
            repository.getTransactions(viewModel.accountId!!, size = Consts.DEFAULT_PAGE_SIZE, dateTo = params.key)?.let {
                it.transactions?.let { transactions ->
                    callback.onResult(transactions)
                }
            }?: invalidate()
        }
    }

    override fun loadBefore(params: LoadParams<Date>, callback: LoadCallback<Transaction>) {

    }

    override fun getKey(item: Transaction): Date {
        return item.dueDate!!
    }

}*/