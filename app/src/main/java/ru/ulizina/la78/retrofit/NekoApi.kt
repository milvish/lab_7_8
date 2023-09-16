package ru.ulizina.la78.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NekoApi {
    @GET("endpoints")
    fun getNekoCategories(): NekoCategories

    @GET("hug")
    suspend fun getRandomNeko(@Query("amount") id:Int): NekoResponse

    @GET("hug")
    suspend fun getFewNekos(@Query("amount") id:Int): NekosList

    @GET("search")
    suspend fun getRandomNekoWithCategories(
        @Query("query") query:String,
        @Query("type") type:Int,
        @Query("category") category:String,
        @Query("amount") amount: Int
    ): NekoResponse
}