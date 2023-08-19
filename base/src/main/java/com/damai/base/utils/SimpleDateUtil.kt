package com.damai.base.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by damai007 on 19/August/2023
 */
class SimpleDateUtil {

    companion object {

        fun parseDateToString(
            givenDate: Date,
            outputFormat: DateFormat
        ): String {
            val format = SimpleDateFormat(outputFormat.pattern, Locale.getDefault())
            return format.format(givenDate)
        }

        fun parseStringToDate(
            givenDateString: String,
            sourceFormat: DateFormat
        ): Date? {
            val format = SimpleDateFormat(sourceFormat.pattern, Locale.getDefault())
            return try {
                format.parse(givenDateString)
            } catch (e: ParseException) {
                null
            } catch (e: RuntimeException) {
                null
            } catch (e: Exception) {
                null
            }
        }
    }

    enum class DateFormat(val pattern: String) {
        DD_MM_YYYY("dd-MM-yyyy"),
        YYYY_MM_DD("yyyy-MM-dd"),
        YYYY("yyyy")
    }
}