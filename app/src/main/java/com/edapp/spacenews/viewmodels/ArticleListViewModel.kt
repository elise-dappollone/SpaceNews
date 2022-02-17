package com.edapp.spacenews.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edapp.spacenews.api.SpaceNewsApiService
import com.edapp.spacenews.api.SpaceNewsRepository
import com.edapp.spacenews.models.SpaceNewsArticle
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ArticleListViewModel(dispatcher: CoroutineDispatcher): ViewModel() {
    private val job = Job()
    private val coroutineContext: CoroutineContext = job + dispatcher
    private val scope = CoroutineScope(coroutineContext)

    var repository = SpaceNewsRepository(SpaceNewsApiService.spaceNewsApi)
    var articleList = emptyList<SpaceNewsArticle>()

    fun onCreateView() {
        val response = scope.async {
            getArticleList(15)
        }
        runBlocking {
            articleList = response.await().toList()
        }
    }

    private suspend fun getArticleList(maxLimit: Int): List<SpaceNewsArticle> {
        return repository.getArticleList(maxLimit) ?: emptyList()
    }
}

@Suppress("UNCHECKED_CAST")
class ArticleListViewModelFactory(private val dispatcher: CoroutineDispatcher) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleListViewModel::class.java)) return ArticleListViewModel(dispatcher) as T
        throw IllegalArgumentException("Unknown ViewModel class")    }
}