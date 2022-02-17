package com.edapp.spacenews.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edapp.spacenews.api.SpaceNewsApiService
import com.edapp.spacenews.api.SpaceNewsRepository
import com.edapp.spacenews.models.SpaceNewsArticle
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ArticleDetailViewModel(dispatcher: CoroutineDispatcher): ViewModel() {
    private val job = Job()
    private val coroutineContext: CoroutineContext = job + dispatcher
    private val scope = CoroutineScope(coroutineContext)

    var repository = SpaceNewsRepository(SpaceNewsApiService.spaceNewsApi)
    lateinit var article: SpaceNewsArticle

    private val emptyArticleDetail = SpaceNewsArticle(0, "", "", "", "", "")

    fun onCreateView(articleId: Int) {
        val response = scope.async {
            getArticleForId(articleId)
        }
        runBlocking {
            article = response.await()
        }
    }

    private suspend fun getArticleForId(id: Int): SpaceNewsArticle {
        return repository.getArticleForId(id) ?: emptyArticleDetail
    }
}

@Suppress("UNCHECKED_CAST")
class ArticleDetailViewModelFactory(private val dispatcher: CoroutineDispatcher) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleDetailViewModel::class.java)) return ArticleDetailViewModel(dispatcher) as T
        throw IllegalArgumentException("Unknown ViewModel class")    }
}