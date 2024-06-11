package com.test.nopstation_cart.repository

import com.test.nopstation_cart.network.api.AccountApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val api: AccountApi
) {
    suspend fun getProfile() = withContext(Dispatchers.IO) {
        return@withContext api.getAccountInfo()
    }
}