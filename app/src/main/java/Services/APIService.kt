package Services

import Interfaces.IAccountAPIService
import Interfaces.ICategoryAPIService
import Interfaces.ITransactionAPIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {
    private const val BASE_URL = "http://delicias-001-site4.rtempurl.com/api/"

    val apiCategory: ICategoryAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ICategoryAPIService::class.java)
    }

    val apiAccount: IAccountAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IAccountAPIService::class.java)
    }

    val apiTransaction: ITransactionAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ITransactionAPIService::class.java)
    }
}