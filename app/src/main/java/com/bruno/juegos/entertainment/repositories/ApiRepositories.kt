package com.bruno.juegos.entertainment.repositories

import com.bruno.juegos.entertainment.model.ResultApi
import com.bruno.juegos.entertainment.net.ApiService
import javax.inject.Inject

class ApiRepositories @Inject constructor(
    val apiService: ApiService
) {

    suspend fun getData(amunt: String) = safeCall {
        apiService.getData(amunt)
    }

    suspend fun <T> safeCall(f: suspend () -> T): ResultApi<T> {
        return try {
            ResultApi.Success(f.invoke())
        } catch (e: Exception) {
            ResultApi.Error(e)
        }
    }


}