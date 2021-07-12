@file:JvmName("TimeUtils")

package com.example.redditapp.commons

import android.util.Log
import java.util.*

// reddit returns time as a Long. This extension function modify the time into human
// readable time (i.e. 2 minutes ago instead of 120 seconds ago).
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

    if (year>0) {
        if (year == 1) {
            sb.append("a year ago")
        } else {
            sb.append("$year years ago")
        }
        return sb.toString()
    }
    if (month>0) {
        if (month == 1) {
            sb.append("a month ago")
        } else {
            sb.append("$month months ago")
        }
        return sb.toString()
    }
    if (day>0) {
        if (day == 1) {
            sb.append("a day ago")
        } else {
            sb.append("$day days ago")
        }
        return sb.toString()
    }
    if (hour>0) {
        if (hour == 1) {
            sb.append("an hour ago")
        } else {
            sb.append("$hour hours ago")
        }
        return sb.toString()
    }
    if (min >0) {
        if (min == 1) {
            sb.append("a min ago")
        } else {
            sb.append("$min minutes ago")
        }
        return sb.toString()
    }
    if (sec >0) {
        if (sec == 1) {
            sb.append("a sec ago")
        } else {
            sb.append("$sec secs ago")
        }
        return sb.toString()
    }
    Log.v("TimeExt", diffInSeconds.toString())
    return "There is a bug in time"
}