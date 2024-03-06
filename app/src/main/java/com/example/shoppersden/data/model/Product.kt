package com.example.shoppersden.data.model

import com.squareup.moshi.Json
import java.text.NumberFormat
import java.util.Locale

data class Product(
    @Json(name = "id") val id: Int,
    @Json(name = "price") val price: Double,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "category") val category: String,
    @Json(name = "image") val imageUrl: String,
    @Json(name = "rating") val rating: Rating
) {
    override fun toString(): String {
        return "id: $id\n" +
                "price: $price\n" +
                "title: $title\n" +
                "desc: $description\n" +
                "category: $category\n" +
                "imageUrl: $imageUrl\n" +
                "rating: $rating\n" +
                "====\n"
    }

    fun getFormattedPrice(): String {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        return numberFormat.format(price)
    }
}
