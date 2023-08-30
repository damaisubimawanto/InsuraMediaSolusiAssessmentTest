package com.damai.insuramediasolusi.modules

import com.damai.insuramediasolusi.navigations.PageNavigationApi
import com.damai.insuramediasolusi.navigations.PageNavigationApiImpl
import org.koin.dsl.module

/**
 * Created by damai007 on 19/August/2023
 */

val pageNavigationModule = module {
    factory<PageNavigationApi> {
        PageNavigationApiImpl()
    }
}