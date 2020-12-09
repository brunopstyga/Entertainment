package com.bruno.juegos.entertainment.net


import com.bruno.juegos.entertainment.model.Response
import com.bruno.juegos.entertainment.model.Results
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api.php")
    suspend fun getData(@Query("amount") amount: String?): Response
}
