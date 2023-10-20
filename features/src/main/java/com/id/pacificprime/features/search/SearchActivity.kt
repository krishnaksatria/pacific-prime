package com.id.pacificprime.features.search

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.id.pacificprime.commons.adapter.itemdecoration.ItemDividerVertical
import com.id.pacificprime.commons.navigation.MovieDetailNavigation
import com.id.pacificprime.features.R
import com.id.pacificprime.features.databinding.ActivitySearchBinding
import com.id.pacificprime.libraries.extension.setVisible
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : ScopeActivity() {

    private lateinit var binding: ActivitySearchBinding

    private val searchViewModel: SearchViewModel by viewModel()

    private val searchAdapter: SearchAdapter by inject()

    private val movieDetailNavigation: MovieDetailNavigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val keyword = intent.getStringExtra(KEYWORD_ARGS) ?: ""
        if (keyword.isNotEmpty()) {
            searchViewModel.keyword = keyword
        } else {
            finish()
            return
        }

        initLayout()
        initEvent()
        initObserver()
        initData()
    }

    private fun initLayout() {
        binding.recyclerMovie.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(
                this@SearchActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(ItemDividerVertical(resources.getDimensionPixelSize(R.dimen.dimens_10dp)))
        }
    }

    private fun initEvent() {
        searchAdapter.setOnMovieClickListener { _, movie ->
            startActivity(movieDetailNavigation.createIntent(this, movie.id))
        }
    }

    private fun initObserver() {
        searchViewModel.getMovieListLiveData.observe(this) {
            binding.layoutEmptyFavorite.setVisible(it.isEmpty())
            searchAdapter.submitList(it)
        }
    }

    private fun initData() {
        searchViewModel.getMovieList()
    }

    companion object {
        const val KEYWORD_ARGS = "keyword_args"
    }
}
