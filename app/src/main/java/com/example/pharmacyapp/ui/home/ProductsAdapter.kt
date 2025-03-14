package com.example.pharmacyapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmacyapp.R
import com.example.pharmacyapp.databinding.ItemProductBinding
import com.example.pharmacyapp.model.Product
import java.text.NumberFormat
import java.util.Locale

class ProductsAdapter(
    private val onProductClick: (Product) -> Unit
) : ListAdapter<Product, ProductsAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onProductClick(getItem(position))
                }
            }
        }

        fun bind(product: Product) {
            val context = binding.root.context
            val currentLocale = context.resources.configuration.locales[0]
            val currencyFormat = NumberFormat.getCurrencyInstance(currentLocale)

            with(binding) {
                productName.text = product.name
                productDescription.text = product.description
                productPrice.text = currencyFormat.format(product.price)
                productCategory.text = product.category
                
                // Here you would load the image using your preferred image loading library
                // Glide.with(context).load(product.imageUrl).into(productImage)
            }
        }
    }

    private class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
} 