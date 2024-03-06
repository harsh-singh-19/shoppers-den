package com.example.shoppersden.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.shoppersden.R
import com.example.shoppersden.databinding.FragmentProductDetailBinding
import com.example.shoppersden.ui.viewmodel.ProductViewModel
import com.example.shoppersden.ui.viewmodel.ProductViewModelFactory

class ProductDetailFragment : Fragment() {

    private val viewModel: ProductViewModel by activityViewModels { ProductViewModelFactory() }
    private val navigationArgs: ProductDetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setCurrentProductId(navigationArgs.productId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }
}