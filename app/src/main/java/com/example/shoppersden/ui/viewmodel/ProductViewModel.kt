package com.example.shoppersden.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.example.shoppersden.data.api.RetrofitBuilder
import com.example.shoppersden.data.model.Product
import com.example.shoppersden.data.util.Resource
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val _products = MutableLiveData<Resource<List<Product>>>()
    private val _currentProductId = MutableLiveData<Int>()

    val products: LiveData<Resource<List<Product>>>
        get() = _products
    val currentProduct: LiveData<Product?> = _currentProductId.map { productId ->
        _products.value?.data?.find { it.id == productId }
    }

    init {
        Log.d("####", "fetching all products...")
        fetchAllProducts()
    }

    fun setCurrentProductId(productId: Int) {
        if (productId != -1) {
            _currentProductId.value = productId
        }
    }

    private fun fetchAllProducts() {
        // TODO("Revisit dispatchers and threads")
        viewModelScope.launch {
            _products.value = Resource.Loading()
            try {
                RetrofitBuilder.retrofitService.getAll().let {
                    _products.value = Resource.Success(it)
                }
            } catch (e: Exception) {
                _products.value = Resource.Error(message = "Error fetching products: $e")
                Log.d("####", "Error fetching products: $e")
            }
        }
    }
}
