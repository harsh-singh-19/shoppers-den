package com.example.shoppersden.data.api

import com.example.shoppersden.data.model.Product
import retrofit2.http.GET

interface ProductApiService {

    @GET("products")
    suspend fun getAll(): List<Product>
}
