package com.example.shoppersden.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shoppersden.R
import com.example.shoppersden.data.util.Resource
import com.example.shoppersden.databinding.FragmentProductsBinding
import com.example.shoppersden.ui.adapter.ProductAdapter
import com.example.shoppersden.ui.viewmodel.ProductViewModel
import com.example.shoppersden.ui.viewmodel.ProductViewModelFactory

class ProductsFragment : Fragment() {

    private val viewModel: ProductViewModel by activityViewModels { ProductViewModelFactory() }

    private lateinit var productsAdapter: ProductAdapter
    private lateinit var binding: FragmentProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Init recyclerview adapter
        productsAdapter = ProductAdapter {
            val action =
                ProductsFragmentDirections.actionProductsFragmentToProductDetailFragment(it.id)
            findNavController().navigate(action)
        }

        // Inflate the layout for this fragment
        binding = FragmentProductsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupRecyclerView()
    }

    private fun setupObservers() {
        viewModel.products.observe(viewLifecycleOwner) { productsRes ->
            when (productsRes) {
                is Resource.Error -> {
                    updateVisibility()
                }

                is Resource.Loading -> {
                    updateVisibility()
                }

                is Resource.Success -> {
                    updateVisibility(View.VISIBLE)
                    productsAdapter.submitList(productsRes.data)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.productsRecyclerView.apply {
            layoutManager = GridLayoutManager(binding.root.context, 2)
            adapter = productsAdapter
        }
    }

    private fun updateVisibility(recyclerViewVisibility: Int = View.GONE) {
        binding.productsRecyclerView.visibility = recyclerViewVisibility
    }
}