package com.example.investmentportfolio.util

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun longToDateString(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
    return sdf.format(Date(timestamp))
}