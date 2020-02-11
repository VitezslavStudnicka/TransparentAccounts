package com.vs.sample.transparentaccounts.utils

import com.vs.sample.transparentaccounts.repository.Repository
import com.vs.sample.transparentaccounts.repository.Rest
import com.vs.sample.transparentaccounts.viewmodels.AccountsViewModelFactory

object InjectorUtils {

    fun provideAccountsVMFactory(): AccountsViewModelFactory {
        val repository = Repository.getInstance(Rest.getInstance())
        return AccountsViewModelFactory(repository)
    }

}