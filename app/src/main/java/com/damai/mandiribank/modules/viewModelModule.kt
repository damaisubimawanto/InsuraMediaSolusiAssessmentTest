package com.damai.mandiribank.modules

import com.damai.mandiribank.ui.detail.MovieDetailViewModel
import com.damai.mandiribank.ui.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by damai007 on 18/August/2023
 */

val viewModelModule = module {
    viewModel {
        MainViewModel(
            app = androidApplication(),
            getMovieGenreListUseCase = get(),
            getMovieItemListUseCase = get(),
            dispatcher = get()
        )
    }
    viewModel {
        MovieDetailViewModel(
            app = androidApplication(),
            getMovieDetailsUseCase = get(),
            getMovieReviewsUseCase = get(),
            getMovieVideosUseCase = get(),
            dispatcher = get()
        )
    }
}