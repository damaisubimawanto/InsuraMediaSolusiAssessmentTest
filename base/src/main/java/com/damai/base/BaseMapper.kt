package com.damai.base

/**
 * Created by damai007 on 18/August/2023
 */
abstract class BaseMapper<in T, out R> {

    abstract fun map(value: T): R

    fun map(value: List<T>): List<R> {
        return value.map {
            map(value = it)
        }
    }
}