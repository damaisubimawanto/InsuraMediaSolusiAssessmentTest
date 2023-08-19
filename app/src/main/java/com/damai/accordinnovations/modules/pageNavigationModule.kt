package com.damai.accordinnovations.modules

import com.damai.accordinnovations.navigations.PageNavigationApi
import com.damai.accordinnovations.navigations.PageNavigationApiImpl
import org.koin.dsl.module

/**
 * Created by damai007 on 19/August/2023
 */

val pageNavigationModule = module {
    factory<PageNavigationApi> {
        PageNavigationApiImpl()
    }
}