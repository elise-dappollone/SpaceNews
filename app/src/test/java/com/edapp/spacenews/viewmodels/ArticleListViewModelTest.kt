package com.edapp.spacenews.viewmodels

import com.edapp.spacenews.api.SpaceNewsRepository
import com.edapp.spacenews.models.SpaceNewsArticle
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ArticleListViewModelTest : TestCase() {

    private val mockRepository = mock<SpaceNewsRepository>()
    private val viewModel = ArticleListViewModel(Dispatchers.Unconfined)

    private val articleOne = SpaceNewsArticle(
        id = 1,
        title = "Space Headline",
        url = "url",
        imageUrl = "imagePath",
        newsSite = "site",
        summary = "cool news summary")

    private val articleTwo = SpaceNewsArticle(
        id = 2,
        title = "Space Headline 2",
        url = "url2",
        imageUrl = "imagePath2",
        newsSite = "site 2",
        summary = "cool news summary 2")

    private val articleThree = SpaceNewsArticle(
        id = 3,
        title = "Space Headline 3",
        url = "url3",
        imageUrl = "imagePath3",
        newsSite = "site 3",
        summary = "cool news summary 3")

    @Before
    fun setup() {
        viewModel.repository = mockRepository
    }

    @Test
    fun testOnCreateSuccess() {
        runBlockingTest {
            doReturn(listOf(articleOne, articleTwo, articleThree)).whenever(mockRepository).getArticleList(any())
            viewModel.onCreateView()
        }
        assertEquals(articleOne, viewModel.articleList[0])
        assertEquals(articleTwo, viewModel.articleList[1])
        assertEquals(articleThree, viewModel.articleList[2])
    }

    @Test
    fun testOnCreateCallFailure() {
        runBlockingTest {
            doReturn(null).whenever(mockRepository).getArticleList(any())
            viewModel.onCreateView()
        }
        assertTrue(viewModel.articleList.isEmpty())
    }
}