package Interfaces

import Entity.CategoryDTO
import retrofit2.http.*

interface ICategoryAPIService {
    @Headers("Content-Type: application/json")
    @GET("Category")
    suspend fun getCategory(): List<CategoryDTO>

    @Headers("Content-Type: application/json")
    @GET("Category/{id}")
    suspend fun getById(id: Int): CategoryDTO

    @Headers("Content-Type: application/json")
    @POST("Category")
    suspend fun addCategory(category: CategoryDTO)

    @Headers("Content-Type: application/json")
    @PUT("Category/{id}")
    suspend fun updateCategory(id: Int, category: CategoryDTO)

    @Headers("Content-Type: application/json")
    @DELETE("Category/{id}")
    suspend fun deleteCategory(id: Int)
}