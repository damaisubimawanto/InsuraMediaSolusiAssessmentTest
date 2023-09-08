package com.damai.mandiribank.modules

import com.damai.data.mappers.MovieGenreResponseToMovieGenreModelMapper
import com.damai.data.mappers.MovieItemResponseToMovieItemModelMapper
import com.damai.data.mappers.MovieVideoResponseToMovieVideoModelMapper
import com.damai.data.mappers.ReviewItemResponseToReviewItemModelMapper
import org.koin.dsl.module

/**
 * Created by damai007 on 18/August/2023
 */

val mapperModule = module {
    factory {
        MovieGenreResponseToMovieGenreModelMapper()
    }
    factory {
        MovieItemResponseToMovieItemModelMapper()
    }
    factory {
        ReviewItemResponseToReviewItemModelMapper()
    }
    factory {
        MovieVideoResponseToMovieVideoModelMapper()
    }
}