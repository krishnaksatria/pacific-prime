package com.id.pacificprime.di

import android.content.Context
import android.content.Intent
import com.id.pacificprime.commons.navigation.DashboardNavigation
import com.id.pacificprime.commons.navigation.FavoriteNavigation
import com.id.pacificprime.commons.navigation.MovieDetailNavigation
import com.id.pacificprime.commons.navigation.SearchNavigation
import com.id.pacificprime.features.dashboard.DashboardActivity
import com.id.pacificprime.features.favorite.FavoriteActivity
import com.id.pacificprime.features.movie.MovieDetailActivity
import com.id.pacificprime.features.search.SearchActivity
import org.koin.dsl.module

val navigationModule = module {
    single<DashboardNavigation> {
        object : DashboardNavigation {
            override fun createIntent(context: Context?) =
                Intent(context, DashboardActivity::class.java)
        }
    }
    single<MovieDetailNavigation> {
        object : MovieDetailNavigation {
            override fun createIntent(context: Context?, movie: Int): Intent {
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.MOVIE_ID_ARGS, movie)
                return intent
            }
        }
    }
    single<FavoriteNavigation> {
        object : FavoriteNavigation {
            override fun createIntent(context: Context?) =
                Intent(context, FavoriteActivity::class.java)
        }
    }
    single<SearchNavigation> {
        object : SearchNavigation {
            override fun createIntent(context: Context?, keyword: String): Intent {
                val intent = Intent(context, SearchActivity::class.java)
                intent.putExtra(SearchActivity.KEYWORD_ARGS, keyword)
                return intent
            }
        }
    }
}
