package com.edapp.spacenews.api

import com.edapp.spacenews.models.SpaceNewsArticle
import java.lang.Exception

class SpaceNewsRepository(private val spaceNewsApi: SpaceNewsApi) {

    suspend fun getArticleList(maxLimit: Int): List<SpaceNewsArticle>? {
        return try {
            spaceNewsApi.getArticleList(maxLimit)
        } catch (exception: Exception) {
            println("Failed to fetch article list")
            null
        }
    }

    suspend fun getArticleForId(articleId: Int): SpaceNewsArticle? {
        return try {
            spaceNewsApi.getArticleForId(articleId)
        } catch (exception: Exception) {
            println("Failed to fetch article for id: $articleId")
            null
        }
    }
}