package com.example.timecapsuleApp.Transactions.repository

import com.example.timecapsuleApp.API.ApiRequests
import com.example.timecapsuleApp.Transactions.models.Transaction
import com.example.timecapsuleApp.View.TransactionsResponse
import retrofit2.Response

class TransactionRepository (private val apiRequests: ApiRequests) {


        suspend fun getTransaction(token: String): List<Transaction> {
            return apiRequests.getTransaction(token)
        }

        suspend fun addTransaction(token: String, newTransaction: Transaction): TransactionsResponse {
            return apiRequests.addTransaction(token, newTransaction)
        }

        suspend fun removeTransaction(token: String, messageId: Long): Response<Void> {
            return apiRequests.removeMessage(token, messageId)
        }
    }
