package com.edapp.spacenews.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.edapp.spacenews.api.SpaceNewsRepository
import com.edapp.spacenews.models.SpaceNewsArticle
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ArticleDetailViewModelTest : TestCase() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewModel = ArticleDetailViewModel(Dispatchers.Unconfined)
    private val mockRepository = mock<SpaceNewsRepository>()

    private val article = SpaceNewsArticle(
        id = 1,
        title = "Space Headline",
        url = "url",
        imageUrl = "imagePath",
        newsSite = "site",
        summary = "cool news summary")
    @Before
    fun setup() {
        viewModel.repository = mockRepository
    }

    @Test
    fun testOnCreateViewSuccess() {
        val articleId = 1
        runBlockingTest {
            doReturn(article).whenever(mockRepository).getArticleForId(articleId)
            viewModel.onCreateView(articleId)
        }
        assertEquals("url", viewModel.article.url)
    }

    @Test
    fun testOnCreateViewCallFailure() {
        val articleId = 1
        runBlockingTest {
            doReturn(null).whenever(mockRepository).getArticleForId(articleId)
            viewModel.onCreateView(articleId)
        }
        assertEquals("", viewModel.article.url)
    }
}