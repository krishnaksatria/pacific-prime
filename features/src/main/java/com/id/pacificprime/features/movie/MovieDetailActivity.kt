package com.id.pacificprime.features.movie

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.id.pacificprime.commons.adapter.itemdecoration.ItemDividerVertical
import com.id.pacificprime.commons.extensions.loadImage
import com.id.pacificprime.commons.extensions.showError
import com.id.pacificprime.commons.extensions.showToast
import com.id.pacificprime.commons.ui.viewmodel.ViewStateModel
import com.id.pacificprime.features.R
import com.id.pacificprime.features.databinding.ActivityMovieDetailBinding
import com.id.pacificprime.libraries.extension.setGone
import com.id.pacificprime.libraries.extension.setVisible
import com.id.pacificprime.room.data.FavoriteMovieData
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : ScopeActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val movieDetailViewModel: MovieDetailViewModel by viewModel()

    private val reviewAdapter: ReviewAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayout()
        initEvent()
        initObserver()
        initData()
    }

    private fun initLayout() {
        binding.recyclerReview.apply {
            adapter = reviewAdapter
            layoutManager = LinearLayoutManager(
                this@MovieDetailActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(ItemDividerVertical(resources.getDimensionPixelSize(R.dimen.dimens_15dp)))
        }
    }

    private fun initEvent() {
        binding.appBarMovieDetail.setOnEndButtonClickListener {
            val movie = movieDetailViewModel.movie
            val favoriteMovie = FavoriteMovieData(
                movie.id,
                movie.posterPath,
                movie.title,
                movie.overview
            )
            if (movieDetailViewModel.isFavorite) {
                movieDetailViewModel.removeFavoriteMovie(favoriteMovie)
                binding.appBarMovieDetail.endButtonSrc = R.drawable.ic_favorite_empty
            } else {
                movieDetailViewModel.insertFavoriteMovie(favoriteMovie)
                binding.appBarMovieDetail.endButtonSrc = R.drawable.ic_favorite_filled
            }
        }
    }

    private fun initObserver() {
        movieDetailViewModel.movieDetailLiveData.observe(this) { movie ->
            val image = getString(R.string.label_image_prefix, movie.posterPath)
            binding.textMovieTitle.text = movie.title
            binding.textMovieDescription.text = movie.overview
            binding.imageMovie.loadImage(image)

            movieDetailViewModel.movie = movie
            movieDetailViewModel.getFavoriteMovieList()
        }

        movieDetailViewModel.movieReviewListLiveData.observe(this) {
            reviewAdapter.submitList(it)
        }

        movieDetailViewModel.getFavoriteMovieListLiveData.observe(this) {
            val movie = movieDetailViewModel.movie
            val favoriteMovie = FavoriteMovieData(
                movie.id,
                movie.posterPath,
                movie.title,
                movie.overview
            )
            movieDetailViewModel.isFavorite = it.contains(favoriteMovie)
            val favoriteResId = if (movieDetailViewModel.isFavorite) {
                R.drawable.ic_favorite_filled
            } else {
                R.drawable.ic_favorite_empty
            }
            binding.appBarMovieDetail.endButtonSrc = favoriteResId
        }

        movieDetailViewModel.insertReminderLiveData.observe(this) {
            movieDetailViewModel.getMovieReviewList()
            showToast(getString(R.string.label_success_insert_data))
        }

        movieDetailViewModel.removeReminderLiveData.observe(this) {
            movieDetailViewModel.getMovieReviewList()
            showToast(getString(R.string.label_success_remove_data))
        }

        movieDetailViewModel.viewState.observe(this) {
            when (it) {
                ViewStateModel.LOADING -> binding.progressBarLoading.setVisible()
                else -> binding.progressBarLoading.setGone()
            }
        }

        movieDetailViewModel.showErrorLiveData.observe(this) { errorMessage ->
            showError(errorMessage)
        }
    }

    private fun initData() {
        movieDetailViewModel.movieId = intent.getIntExtra(MOVIE_ID_ARGS, 0)
        movieDetailViewModel.getMovieDetail()
        movieDetailViewModel.getMovieReviewList()
    }

    companion object {
        internal const val INSERT_DATA_FAILED = -1L
        internal const val CHANGE_DATA_SUCCESS = 1
        const val MOVIE_ID_ARGS = "movie_id_args"
    }
}
