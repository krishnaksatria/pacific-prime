package com.id.pacificprime.features.dashboard

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.id.pacificprime.commons.adapter.itemdecoration.ItemDividerHorizontal
import com.id.pacificprime.commons.extensions.showError
import com.id.pacificprime.commons.navigation.FavoriteNavigation
import com.id.pacificprime.commons.navigation.MovieDetailNavigation
import com.id.pacificprime.commons.navigation.SearchNavigation
import com.id.pacificprime.commons.ui.viewmodel.ViewStateModel
import com.id.pacificprime.features.R
import com.id.pacificprime.features.databinding.ActivityDashboardBinding
import com.id.pacificprime.libraries.extension.setGone
import com.id.pacificprime.libraries.extension.setVisible
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class DashboardActivity : ScopeActivity() {

    private lateinit var binding: ActivityDashboardBinding

    private val dashboardViewModel: DashboardViewModel by viewModel()

    private val nowPlayingAdapter: MovieAdapter by inject(
        qualifier = named(
            TYPE_NOW_PLAYING
        )
    )
    private val popularAdapter: MovieAdapter by inject(
        qualifier = named(
            TYPE_POPULAR
        )
    )
    private val topRatedAdapter: MovieAdapter by inject(
        qualifier = named(
            TYPE_TOP_RATED
        )
    )
    private val upcomingAdapter: MovieAdapter by inject(
        qualifier = named(
            TYPE_UPCOMING
        )
    )

    private val movieDetailNavigation: MovieDetailNavigation by inject()
    private val favoriteNavigation: FavoriteNavigation by inject()
    private val searchNavigation: SearchNavigation by inject()

    private val pushNotificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dashboardViewModel.saveToken(TOKEN)

        initLayout()
        initEvent()
        initObserver()
        initData()
        initPermission()
    }

    private fun initData() {
        dashboardViewModel.getNowPlayingMovieList()
        dashboardViewModel.getPopularMovieList()
        dashboardViewModel.getTopRatedMovieList()
        dashboardViewModel.getUpcomingMovieList()
    }

    private fun initLayout() {
        binding.recyclerNowPlaying.apply {
            adapter = nowPlayingAdapter
            layoutManager = LinearLayoutManager(
                this@DashboardActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(ItemDividerHorizontal(resources.getDimensionPixelSize(R.dimen.dimens_8dp)))
        }

        binding.recyclerPopularMovie.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(
                this@DashboardActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(ItemDividerHorizontal(resources.getDimensionPixelSize(R.dimen.dimens_10dp)))
        }

        binding.recyclerTopRated.apply {
            adapter = topRatedAdapter
            layoutManager = LinearLayoutManager(
                this@DashboardActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(ItemDividerHorizontal(resources.getDimensionPixelSize(R.dimen.dimens_8dp)))
        }

        binding.recyclerUpcoming.apply {
            adapter = upcomingAdapter
            layoutManager = LinearLayoutManager(
                this@DashboardActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(ItemDividerHorizontal(resources.getDimensionPixelSize(R.dimen.dimens_8dp)))
        }
    }

    private fun initEvent() {
        binding.appBarDashboard.ivFavoriteButton.setOnClickListener {
            startActivity(favoriteNavigation.createIntent(this))
        }

        binding.searchBox.etKeyword.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                startActivity(searchNavigation.createIntent(this, v.text.toString()))
            }
            true
        }

        nowPlayingAdapter.setOnMovieClickListener { _, movie ->
            startActivity(movieDetailNavigation.createIntent(this, movie.id))
        }

        popularAdapter.setOnMovieClickListener { _, movie ->
            startActivity(movieDetailNavigation.createIntent(this, movie.id))
        }

        topRatedAdapter.setOnMovieClickListener { _, movie ->
            startActivity(movieDetailNavigation.createIntent(this, movie.id))
        }

        upcomingAdapter.setOnMovieClickListener { _, movie ->
            startActivity(movieDetailNavigation.createIntent(this, movie.id))
        }
    }

    private fun initObserver() {
        dashboardViewModel.nowPlayingMovieListLiveData.observe(this) {
            nowPlayingAdapter.submitList(it)
        }

        dashboardViewModel.popularMovieListLiveData.observe(this) {
            popularAdapter.submitList(it)
        }

        dashboardViewModel.topRatedMovieListLiveData.observe(this) {
            topRatedAdapter.submitList(it)
        }

        dashboardViewModel.upcomingMovieListLiveData.observe(this) {
            upcomingAdapter.submitList(it)
        }

        dashboardViewModel.viewState.observe(this) {
            when (it) {
                ViewStateModel.LOADING -> binding.progressBarLoading.setVisible()
                else -> binding.progressBarLoading.setGone()
            }
        }

        dashboardViewModel.showErrorLiveData.observe(this) { errorMessage ->
            showError(errorMessage)
        }
    }

    private fun initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !hasPostNotificationPermission()) {
            pushNotificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun hasPostNotificationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        const val TYPE_POPULAR = "popular"
        const val TYPE_UPCOMING = "upcoming"
        const val TYPE_TOP_RATED = "top_rated"
        const val TYPE_NOW_PLAYING = "now_playing"
        private const val TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjMjcwOTdhNzllZDVlMzAyZDcxNDU1MWZlNjFhNjY2NyIsInN1YiI6IjY0ODY5YjhlZTM3NWMwMDBjNTI4YTJmZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.3qqiQQkqhV-JKvTkgTngmDOo57bcS_l1pEHL0KTI6hs"
    }
}
