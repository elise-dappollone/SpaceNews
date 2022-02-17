package com.edapp.spacenews.api

import com.edapp.spacenews.models.SpaceNewsArticle
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpaceNewsApi {

    @GET("articles")
    suspend fun getArticleList(@Query("_limit")maxLimit: Int) : List<SpaceNewsArticle>

    @GET("articles/{id}")
    suspend fun getArticleForId(@Path("id") id: Int): SpaceNewsArticle
}