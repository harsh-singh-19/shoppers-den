package com.example.shoppersden.data.model

import com.squareup.moshi.Json

data class Rating(
    @Json(name = "rate") val average: Float,
    @Json(name = "count") val total: Int
) {
    override fun toString(): String {
        return "$average | $total"
    }
}
