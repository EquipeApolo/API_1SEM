package com.cgmdigitalhouse.cinelist.account.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cgmdigitalhouse.cinelist.account.model.AccountModel
import com.cgmdigitalhouse.cinelist.account.repository.AccountRepository

class AccountViewModel(
    private val repository: AccountRepository
): ViewModel() {
    val account = MutableLiveData<AccountModel>()

    fun getAccount() {
        repository.getAccount {
            account.value = it
        }
    }

    class AccountViewModelFactory(
        private val repository: AccountRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AccountViewModel(repository) as T
        }
    }
}


