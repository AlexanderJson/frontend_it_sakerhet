package com.example.timecapsuleApp.Transactions.models

data class Transaction(
    val id: Long = 0,
    val userId: Int = 0,
    val message: String = "",
    val createdAt: String = ""
)
