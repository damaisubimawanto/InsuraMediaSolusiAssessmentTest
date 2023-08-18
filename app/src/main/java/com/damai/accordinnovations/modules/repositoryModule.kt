package com.damai.accordinnovations.modules

import com.damai.data.repos.HomeRepositoryImpl
import com.damai.domain.repositories.HomeRepository
import org.koin.dsl.module

/**
 * Created by damai007 on 18/August/2023
 */

val repositoryModule = module {
    single<HomeRepository> {
        HomeRepositoryImpl(
            homeService = get(),
            dispatcher = get(),
            movieGenreMapper = get()
        )
    }
}