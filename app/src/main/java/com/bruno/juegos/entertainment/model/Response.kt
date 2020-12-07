package com.bruno.juegos.entertainment.model


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("response_code")
    val responseCode: String,
    val results: List<Results>
)