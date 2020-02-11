package com.vs.sample.transparentaccounts.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vs.sample.transparentaccounts.models.Account
import com.vs.sample.transparentaccounts.repository.Repository
import com.vs.sample.transparentaccounts.utils.notifyObserver
import com.vs.sample.transparentaccounts.utils.randomString
import kotlinx.coroutines.launch

class FragmentAccountsVM internal constructor(private val repo: Repository): ViewModel() {
    private val _accounts = MutableLiveData<List<Account>>(ArrayList())

    val accounts : LiveData<List<Account>>
        get() = _accounts

    fun getAccounts() {
        viewModelScope.launch {
            repo.getAccounts()?.let {
                _accounts.value = it
            }
        }
    }

    fun getMoreMockAccount() {
        viewModelScope.launch {
            (_accounts.value as ArrayList).addAll(mockMoreRandomData())
            _accounts.notifyObserver()
        }
    }

    private fun mockMoreRandomData(): ArrayList<Account> {
        return arrayListOf(Account(randomString()), Account(randomString()), Account(randomString()), Account(randomString()))
    }
}
