package com.rifki.jetpackpro.mynoteapps.helper

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }
}