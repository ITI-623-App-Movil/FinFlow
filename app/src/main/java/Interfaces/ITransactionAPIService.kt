package Interfaces

import Entity.TransactionDTO
import retrofit2.http.*

interface ITransactionAPIService {
    @Headers("Content-Type: application/json")
    @GET("Transaction")
    suspend fun getTransaction(): List<TransactionDTO>

    @Headers("Content-Type: application/json")
    @GET("Transaction/{id}")
    suspend fun getById(@Path("id") id: Int): TransactionDTO

    @Headers("Content-Type: application/json")
    @POST("Transaction")
    suspend fun addTransaction(@Body transaction: TransactionDTO)

    @Headers("Content-Type: application/json")
    @PUT("Transaction/{id}")
    suspend fun updateTransaction(@Path("id") id: Int, @Body transaction: TransactionDTO)

    @Headers("Content-Type: application/json")
    @DELETE("Transaction/{id}")
    suspend fun deleteTransaction(@Path("id") id: Int)
}