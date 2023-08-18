package com.damai.base.extensions

/**
 * Created by damai007 on 18/August/2023
 */

fun Int?.orZero() = this ?: 0

fun Boolean?.orFalse() = this ?: false

fun Double?.orZero() = this ?: 0.0