package com.joesemper.pushupboard.data.datasourse.converters

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

const val DATE_FORMAT = "dd.MM.YYYY"

fun getCurrentDateInMilliseconds() = Date().time

fun millisecondsToDateString(ms: Long): String {
    val sdf = SimpleDateFormat.getInstanceForSkeleton(DATE_FORMAT)
    return sdf.format(Date(ms))
}

fun getDatesFromStart(startDate: Long, n: Int): List<Long> {
    val result = mutableListOf<Long>()
    repeat(n) {
        result.add(startDate + TimeUnit.DAYS.toMillis(it.toLong()))
    }
    return result
}