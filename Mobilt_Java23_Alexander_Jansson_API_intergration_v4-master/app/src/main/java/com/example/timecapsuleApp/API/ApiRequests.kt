package com.example.timecapsuleApp.API

import com.example.timecapsuleApp.Transactions.models.Transaction
import com.example.timecapsuleApp.Users.models.User
import com.example.timecapsuleApp.View.TransactionsResponse
import com.example.timecapsuleApp.View.UsersResponse

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiRequests {



    @DELETE("messages/remove")
    suspend fun removeMessage(
        @Header ("Authorization") token: String,
        @Query("messageId") messageId: Long
    ): Response<Void>

    @POST("messages/add")
    suspend fun addTransaction(
       @Header ("Authorization") token: String, //JWT token , user details
       @Body transaction: Transaction // transaction details to add
    ): TransactionsResponse



    @GET("messages/user")
   suspend  fun getTransaction(
        @Header("Authorization") token: String,
    ): List<Transaction>


    @GET("/users/get/")
    suspend fun getUsers(
        @Header("Authorization") token: String,
    ): List<User>


    @POST("/users/register")
    suspend fun registerUser(@Body user: User): Response<Void>


    @POST("/authenticate")
    suspend fun authenticateUser(@Body user: User): Response<UsersResponse>
}


