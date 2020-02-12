package com.vs.sample.transparentaccounts.utils

import com.vs.sample.transparentaccounts.repository.Repository
import com.vs.sample.transparentaccounts.repository.Rest
import com.vs.sample.transparentaccounts.viewmodels.AccountsViewModelFactory
import com.vs.sample.transparentaccounts.viewmodels.TransactionsViewModelFactory

object InjectorUtils {

    fun provideAccountsVMFactory() = AccountsViewModelFactory(Repository.getInstance(Rest.getInstance()))

    fun provideTransactionsVMFactory() = TransactionsViewModelFactory(Repository.getInstance(Rest.getInstance()))

    fun provideRepository() = Repository.getInstance(Rest.getInstance())

}