package com.id.pacificprime.di

import com.id.pacificprime.features.dashboard.DashboardActivity
import com.id.pacificprime.features.dashboard.DashboardViewModel
import com.id.pacificprime.features.dashboard.MovieAdapter
import com.id.pacificprime.features.favorite.FavoriteActivity
import com.id.pacificprime.features.favorite.FavoriteAdapter
import com.id.pacificprime.features.favorite.FavoriteViewModel
import com.id.pacificprime.features.movie.MovieDetailActivity
import com.id.pacificprime.features.movie.MovieDetailViewModel
import com.id.pacificprime.features.movie.ReviewAdapter
import com.id.pacificprime.features.search.SearchActivity
import com.id.pacificprime.features.search.SearchAdapter
import com.id.pacificprime.features.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val featureModule = module {
    scope(named<DashboardActivity>()) {
        viewModel {
            DashboardViewModel(
                movieRepositoryContract = get(),
                preferenceRepositoryContract = get()
            )
        }
        scoped(named(DashboardActivity.TYPE_NOW_PLAYING)) {
            MovieAdapter()
        }
        scoped(named(DashboardActivity.TYPE_POPULAR)) {
            MovieAdapter()
        }
        scoped(named(DashboardActivity.TYPE_TOP_RATED)) {
            MovieAdapter()
        }
        scoped(named(DashboardActivity.TYPE_UPCOMING)) {
            MovieAdapter()
        }
    }
    scope(named<MovieDetailActivity>()) {
        viewModel {
            MovieDetailViewModel(
                movieRepositoryContract = get(),
                roomRepositoryContract = get()
            )
        }
        scoped { ReviewAdapter() }
    }
    scope(named<FavoriteActivity>()) {
        viewModel {
            FavoriteViewModel(
                roomRepositoryContract = get()
            )
        }
        scoped { FavoriteAdapter() }
    }
    scope(named<SearchActivity>()) {
        viewModel {
            SearchViewModel(
                movieRepositoryContract = get()
            )
        }
        scoped { SearchAdapter() }
    }
}
