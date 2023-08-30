package com.damai.insuramediasolusi.modules

import com.damai.base.coroutines.DispatcherProvider
import com.damai.base.coroutines.DispatcherProviderImpl
import com.damai.base.utils.Constants.API_READ_ACCESS_TOKEN
import com.damai.base.utils.Constants.BASE_URL
import com.damai.base.utils.Constants.HEADER_ACCEPT
import com.damai.base.utils.Constants.HEADER_ACCEPT_JSON
import com.damai.base.utils.Constants.HEADER_AUTHORIZATION
import com.damai.base.utils.Constants.HEADER_BEARER
import com.damai.base.utils.Constants.TIMEOUT
import com.damai.data.apiservices.HomeService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by damai007 on 18/August/2023
 */

val networkModule = module {
    single { GsonBuilder().create() }

    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder().apply{
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            cache(null)
            addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header(HEADER_AUTHORIZATION, "$HEADER_BEARER $API_READ_ACCESS_TOKEN")
                    .header(HEADER_ACCEPT, HEADER_ACCEPT_JSON)
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }
            addInterceptor(logging)
        }.build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }

    factory {
        get<Retrofit>().create(HomeService::class.java)
    }

    factory<DispatcherProvider> {
        DispatcherProviderImpl()
    }
}