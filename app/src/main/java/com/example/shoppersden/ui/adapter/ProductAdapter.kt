package com.example.shoppersden.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppersden.data.model.Product
import com.example.shoppersden.databinding.ProductGridListItemBinding

class ProductAdapter(
    val onItemClicked: (product: Product) -> Unit
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(DiffUtilCallback) {

    class ProductViewHolder(
        private val binding: ProductGridListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.apply {
                bindImage(product.imageUrl)

                productTitle.text = product.title
                productPrice.text = product.getFormattedPrice()
                productRating.text = product.rating.toString()
            }
        }

        private fun bindImage(imageUrl: String) {
            Glide.with(binding.root.context)
                .load(imageUrl)
                .into(binding.productImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val viewHolder = ProductViewHolder(
            ProductGridListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        viewHolder.itemView.setOnClickListener {
            onItemClicked(getItem(viewHolder.layoutPosition))
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffUtilCallback : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}
