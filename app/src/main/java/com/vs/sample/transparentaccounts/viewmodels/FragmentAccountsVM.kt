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
    private val _refreshing = MutableLiveData<Boolean>(false)

    val accounts : LiveData<List<Account>>
        get() = _accounts

    val refreshing : LiveData<Boolean>
        get() = _refreshing


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

//    fun getMoreMockAccounts() {
//        viewModelScope.launch {
//            _refreshing.value = true
//            (_accounts.value as ArrayList).addAll(mockMoreRandomData())
//            _accounts.notifyObserver()
//            _refreshing.value = false
//        }
//    }

//    private fun mockMoreRandomData(): ArrayList<Account> {
//        return arrayListOf(Account(randomString()), Account(randomString()), Account(randomString()), Account(randomString()))
//    }
}
