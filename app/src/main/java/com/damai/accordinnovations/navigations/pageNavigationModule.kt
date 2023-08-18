package com.damai.accordinnovations.navigations

import org.koin.dsl.module

/**
 * Created by damai007 on 18/August/2023
 */

val pageNavigationModule = module {
    factory<PageNavigationApi> {
        PageNavigationApiImpl()
    }
}