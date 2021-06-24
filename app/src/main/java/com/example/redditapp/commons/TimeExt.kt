@file:JvmName("TimeUtils")

package com.example.redditapp.commons

import java.util.*

fun Long.getFriendlyTime(): String {
    val dateTime = Date(this * 1000)
    val sb = StringBuffer()
    val current = Calendar.getInstance().time
    var diffInSeconds = ((current.time - dateTime.time)/1000).toInt()

    val sec = if (diffInSeconds >= 60) (diffInSeconds % 60 ) else diffInSeconds

    // here it effectively becomes diff in minutes
    diffInSeconds /= 60
    val min = if (diffInSeconds >= 60) (diffInSeconds % 60 ) else diffInSeconds

    // here it effectively becomes diff in hours
    diffInSeconds /= 60
    val hour = if (diffInSeconds >= 24) (diffInSeconds % 24 ) else diffInSeconds

    // here it effectively becomes diff in days
    diffInSeconds /= 24
    val day = if (diffInSeconds >= 30) (diffInSeconds % 30 ) else diffInSeconds

    // here it effectively becomes diff in months
    diffInSeconds /= 30
    val month = if (diffInSeconds >= 12) (diffInSeconds % 12 ) else diffInSeconds

    diffInSeconds /= 12
    val year = diffInSeconds

    if (year > 0) {
        if (year == 1) {
            sb.append("a year")
        } else {
            sb.append("$year years")
        }
        if (month>0) {
            if (month == 1) {
                sb.append("and a month")
            } else {
                sb.append("and $month months")
            }
        }
        if (day>0) {
            if (day == 1) {
                sb.append("and a day")
            } else {
                sb.append("and $day days")
            }
        }
        if (hour>0) {
            if (hour == 1) {
                sb.append("and an hour")
            } else {
                sb.append("and $hour hours")
            }
        }
        if (min >0) {
            if (min == 1) {
                sb.append("and a min")
            } else {
                sb.append("and $min minutes")
            }
        }
        if (sec >0) {
            if (sec == 1) {
                sb.append("and a sec")
            } else {
                sb.append("and $sec secs")
            }
        }
    }

    sb.append(" ago")
    return sb.toString()
}