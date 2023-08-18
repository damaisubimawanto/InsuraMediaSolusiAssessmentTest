package com.damai.accordinnovations.modules

import com.damai.domain.usecases.GetMovieGenreListUseCase
import org.koin.dsl.module

/**
 * Created by damai007 on 18/August/2023
 */

val useCaseModule = module {
    single {
        GetMovieGenreListUseCase(
            homeRepository = get()
        )
    }
}