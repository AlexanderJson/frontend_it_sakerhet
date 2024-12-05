package com.example.timecapsuleApp.Users.repository

import com.example.timecapsuleApp.API.ApiRequests
import com.example.timecapsuleApp.Users.models.User
import com.example.timecapsuleApp.View.UsersResponse
import retrofit2.Response

class UserRepository (private val transactionApi: ApiRequests) {


    suspend fun getUser(token: String): List<User> {
        return transactionApi.getUsers(token)
    }

    suspend fun registerUser(newUser: User): Response<Void> {
        return transactionApi.registerUser(newUser)
    }

    suspend fun authenticateUser(newUser: User): Response<UsersResponse> {
        return transactionApi.authenticateUser(newUser)
    }
}
