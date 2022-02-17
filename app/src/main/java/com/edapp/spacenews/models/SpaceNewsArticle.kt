package com.edapp.spacenews.models

import java.io.Serializable

data class SpaceNewsArticle (
    val id: Int,
    val title: String,
    val url: String,
    val imageUrl: String,
    val newsSite: String,
    val summary: String
) : Serializable
