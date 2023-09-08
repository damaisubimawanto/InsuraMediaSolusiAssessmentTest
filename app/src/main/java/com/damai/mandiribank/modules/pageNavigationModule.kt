package com.damai.mandiribank.modules

import com.damai.mandiribank.navigations.PageNavigationApi
import com.damai.mandiribank.navigations.PageNavigationApiImpl
import org.koin.dsl.module

/**
 * Created by damai007 on 19/August/2023
 */

val pageNavigationModule = module {
    factory<PageNavigationApi> {
        PageNavigationApiImpl()
    }
}