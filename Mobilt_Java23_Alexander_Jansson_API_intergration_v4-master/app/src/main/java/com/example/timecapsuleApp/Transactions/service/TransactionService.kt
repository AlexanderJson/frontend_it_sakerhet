package com.example.timecapsuleApp.Transactions.service

import android.content.Context
import android.util.Log
import com.example.timecapsuleApp.API.ApiRequests
import com.example.timecapsuleApp.Transactions.models.Transaction
import com.example.timecapsuleApp.API.security.SharedPreferencesUtil
import com.example.timecapsuleApp.API.security.SharedPreferencesUtil.getJwtToken
import com.example.timecapsuleApp.Transactions.repository.TransactionRepository

class TransactionService(private val apiClient: ApiRequests, private val context: Context) {

    private val transactionRepository = TransactionRepository(apiClient)



    suspend fun removeTransaction(context: Context, messageId: Long) {

        val token = getJwtToken(context)

        if (token != null) {
            val authHeader = "Bearer $token"

            try {
                val response = transactionRepository.removeTransaction(authHeader, messageId)
                if (response.isSuccessful){
                    Log.d("TransactionService REMOVE ", "Message: ${messageId} removed successfully: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.d("TransactionService", "Transaction removed UNSUCCESSFULLY")
            }

        }
    }

    suspend fun addTransaction(context: Context, transaction: Transaction) {

        // hämtar jwt token från shared prefs
        val token = getJwtToken(context)

        if (token != null) {
            val authHeader = "Bearer $token"

            try {
                val response = transactionRepository.addTransaction(authHeader, transaction)
                Log.d("TransactionService ADD ", "Transaction added successfully: ${response}")
            } catch (e: Exception) {
                Log.d("TransactionService", "Transaction added UNSUCCESSFULLY")
            }

        }
    }

    suspend fun getTransaction(): List<Transaction>? {
        val token = SharedPreferencesUtil.getJwtToken(context)
        val authHeader = "Bearer $token"
        return try {
            val transaction = transactionRepository.getTransaction(authHeader)
            Log.d("TransactionService GET", "Transactions recieved: ${transaction}")
            transaction
        }
        catch (e: Exception){
            Log.d("TransactionService GET", "No transactions, buy more")
            null
        }
    }
}
