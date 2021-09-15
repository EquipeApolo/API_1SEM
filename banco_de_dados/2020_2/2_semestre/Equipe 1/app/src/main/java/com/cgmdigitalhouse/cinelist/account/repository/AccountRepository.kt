package com.cgmdigitalhouse.cinelist.account.repository

import android.content.Context
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.account.model.AccountModel
import com.google.firebase.auth.FirebaseAuth

class AccountRepository(private val context: Context) {
    private var auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    val account = AccountModel(currentUser!!.photoUrl, currentUser.displayName, currentUser.email)

    fun getAccount(callback: (account: AccountModel) -> Unit) {
        callback.invoke(account)
    }
}