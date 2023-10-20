package com.id.pacificprime.features.favorite

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.id.pacificprime.commons.adapter.itemdecoration.ItemDividerVertical
import com.id.pacificprime.commons.navigation.MovieDetailNavigation
import com.id.pacificprime.features.R
import com.id.pacificprime.features.databinding.ActivityFavoriteBinding
import com.id.pacificprime.libraries.extension.setVisible
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : ScopeActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private val favoriteAdapter: FavoriteAdapter by inject()

    private val movieDetailNavigation: MovieDetailNavigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayout()
        initEvent()
        initObserver()
    }

    override fun onResume() {
        super.onResume()
        favoriteViewModel.getFavoriteMovieList()
    }

    private fun initLayout() {
        binding.recyclerMovie.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(
                this@FavoriteActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(ItemDividerVertical(resources.getDimensionPixelSize(R.dimen.dimens_10dp)))
        }
    }

    private fun initEvent() {
        favoriteAdapter.setOnMovieClickListener { _, movie ->
            startActivity(movieDetailNavigation.createIntent(this, movie.id))
        }
    }

    private fun initObserver() {
        favoriteViewModel.getFavoriteMovieListLiveData.observe(this) {
            binding.layoutEmptyFavorite.setVisible(it.isEmpty())
            favoriteAdapter.submitList(it)
        }
    }
}
