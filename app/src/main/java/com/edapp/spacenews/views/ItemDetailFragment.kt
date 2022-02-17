package com.edapp.spacenews.views

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.edapp.spacenews.databinding.FragmentItemDetailBinding
import com.edapp.spacenews.viewmodels.ArticleDetailViewModel
import com.edapp.spacenews.viewmodels.ArticleDetailViewModelFactory
import kotlinx.coroutines.Dispatchers

class ItemDetailFragment : Fragment() {

    private var itemId: Int = 0

    private lateinit var viewModel: ArticleDetailViewModel
    private lateinit var viewModelFactory: ArticleDetailViewModelFactory

    private lateinit var binding: FragmentItemDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            itemId = it.getInt(ARG_ITEM_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val webView = binding.webView
        val rootView = binding.root

        viewModelFactory = ArticleDetailViewModelFactory(Dispatchers.Default)

        viewModel = ViewModelProvider(this, viewModelFactory)[ArticleDetailViewModel::class.java]
        viewModel.onCreateView(itemId)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                 return false
            }
        }
        loadArticle(webView)

        return rootView
    }

    private fun loadArticle(webView: WebView?) {
        if (viewModel.article.url.isNotEmpty()) {
            webView?.loadUrl(viewModel.article.url)
        }
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }

}