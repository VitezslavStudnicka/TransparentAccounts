@file:Suppress("UNCHECKED_CAST")

package com.vs.sample.transparentaccounts.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vs.sample.transparentaccounts.repository.Repository

class AccountsViewModelFactory(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FragmentAccountsVM(repository) as T
    }
}