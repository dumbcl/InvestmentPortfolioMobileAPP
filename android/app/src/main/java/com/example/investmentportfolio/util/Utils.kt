package com.example.investmentportfolio.util

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val API_ENDPOINT = "https://stock-vnzd.onrender.com/api/v1/"
const val HEADER_AUTH = "Authorization"
const val CONNECT_TIMEOUT = 120L
const val READ_TIMEOUT = 120L
const val WRITE_TIMEOUT = 90L

fun longToDateString(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
    return sdf.format(Date(timestamp))
}