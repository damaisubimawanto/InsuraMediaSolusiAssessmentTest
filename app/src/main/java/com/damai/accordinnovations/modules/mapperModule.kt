package com.damai.accordinnovations.modules

import com.damai.data.mappers.MovieGenreResponseToMovieGenreModelMapper
import org.koin.dsl.module

/**
 * Created by damai007 on 18/August/2023
 */

val mapperModule = module {
    factory {
        MovieGenreResponseToMovieGenreModelMapper()
    }
}