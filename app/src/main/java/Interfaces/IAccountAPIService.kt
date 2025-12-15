package Interfaces

import Entity.AccountDTO
import retrofit2.http.*

interface IAccountAPIService {
    @Headers("Content-Type: application/json")
    @GET("Account")
    suspend fun getAccount(): List<AccountDTO>

    @Headers("Content-Type: application/json")
    @GET("Account/{id}")
    suspend fun getById(id: Int): AccountDTO

    @Headers("Content-Type: application/json")
    @POST("Account")
    suspend fun addAccount(account: AccountDTO)

    @Headers("Content-Type: application/json")
    @PUT("Account/{id}")
    suspend fun updateAccount(id: Int, account: AccountDTO)

    @Headers("Content-Type: application/json")
    @DELETE("Account/{id}")
    suspend fun deleteAccount(id: Int)
}