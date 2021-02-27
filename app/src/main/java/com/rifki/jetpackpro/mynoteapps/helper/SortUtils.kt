package com.rifki.jetpackpro.mynoteapps.helper

import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder

object SortUtils {
    const val LATEST = "Latest"
    const val OLDEST = "Oldest"
    const val RANDOM = "Random"

    fun getSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * From note ")
        when (filter) {
            LATEST -> {
                simpleQuery.append("ORDER BY id DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY id ASC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}