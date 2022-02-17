package com.edapp.spacenews.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.edapp.spacenews.SimpleItemRecyclerViewAdapter
import com.edapp.spacenews.databinding.FragmentItemListBinding
import com.edapp.spacenews.viewmodels.ArticleListViewModel
import com.edapp.spacenews.viewmodels.ArticleListViewModelFactory
import kotlinx.coroutines.Dispatchers


class ItemListFragment : Fragment() {

    private lateinit var viewModel: ArticleListViewModel
    private lateinit var viewModelFactory: ArticleListViewModelFactory

    private lateinit var binding: FragmentItemListBinding

    private lateinit var adapter: SimpleItemRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModelFactory = ArticleListViewModelFactory(Dispatchers.Default)
        viewModel = ViewModelProvider(this, viewModelFactory)[ArticleListViewModel::class.java]

        binding = FragmentItemListBinding.inflate(inflater, container, false)
        recyclerView = binding.itemList

        viewModel.onCreateView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(recyclerView)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        adapter = SimpleItemRecyclerViewAdapter(
            requireActivity() as ArticleListActivity,
            viewModel.articleList,
        )
        recyclerView.adapter = adapter
    }
}