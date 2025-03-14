package com.example.pharmacyapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pharmacyapp.R
import com.example.pharmacyapp.databinding.FragmentHomeBinding
import com.example.pharmacyapp.model.Product
import com.example.pharmacyapp.repository.PurchaseRepository
import com.example.pharmacyapp.ui.adapters.ProductsAdapter
import com.google.android.material.textfield.TextInputEditText

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var productsAdapter: ProductsAdapter
    private var allProducts = mutableListOf<Product>()
    private val purchaseRepository = PurchaseRepository.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupRecyclerView()
        setupSearch()
        loadSampleData()
    }

    private fun setupUI() {
        with(binding) {
            homeTitle.text = getString(R.string.home_title)
            categoriesTitle.text = getString(R.string.categories_title)
            popularProductsTitle.text = getString(R.string.popular_products)
        }
    }

    private fun setupRecyclerView() {
        productsAdapter = ProductsAdapter { product -> handleProductClick(product) }
        
        binding.productsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = productsAdapter
        }
    }

    private fun setupSearch() {
        val searchEditText = binding.root.findViewById<TextInputEditText>(R.id.searchEditText)
        searchEditText?.addTextChangedListener { text ->
            val query = text?.toString()?.lowercase() ?: ""
            if (query.isEmpty()) {
                productsAdapter.submitList(allProducts)
            } else {
                val filteredProducts = allProducts.filter { product ->
                    product.name.lowercase().contains(query) ||
                    product.description.lowercase().contains(query) ||
                    product.category.lowercase().contains(query)
                }
                productsAdapter.submitList(filteredProducts)
            }
        }
    }

    private fun loadSampleData() {
        allProducts = createSampleProducts()
        productsAdapter.submitList(allProducts)
    }

    private fun createSampleProducts(): MutableList<Product> {
        return mutableListOf(
            Product(
                id = "1",
                name = getString(R.string.product_1_name),
                description = getString(R.string.product_1_desc),
                price = 299.99,
                category = getString(R.string.category_painkillers),
                isPopular = true
            ),
            Product(
                id = "2",
                name = getString(R.string.product_2_name),
                description = getString(R.string.product_2_desc),
                price = 199.99,
                category = getString(R.string.category_painkillers)
            ),
            Product(
                id = "3",
                name = getString(R.string.product_3_name),
                description = getString(R.string.product_3_desc),
                price = 249.99,
                category = getString(R.string.category_painkillers)
            ),
            Product(
                id = "4",
                name = getString(R.string.product_4_name),
                description = getString(R.string.product_4_desc),
                price = 599.99,
                category = getString(R.string.category_antibiotics),
                isPopular = true
            ),
            Product(
                id = "5",
                name = getString(R.string.product_5_name),
                description = getString(R.string.product_5_desc),
                price = 399.99,
                category = getString(R.string.category_vitamins)
            ),
            Product(
                id = "6",
                name = getString(R.string.product_6_name),
                description = getString(R.string.product_6_desc),
                price = 449.99,
                category = getString(R.string.category_vitamins)
            ),
            Product(
                id = "7",
                name = getString(R.string.product_7_name),
                description = getString(R.string.product_7_desc),
                price = 899.99,
                category = getString(R.string.category_vitamins),
                isPopular = true
            ),
            Product(
                id = "8",
                name = getString(R.string.product_8_name),
                description = getString(R.string.product_8_desc),
                price = 499.99,
                category = getString(R.string.category_cold)
            ),
            Product(
                id = "9",
                name = getString(R.string.product_9_name),
                description = getString(R.string.product_9_desc),
                price = 349.99,
                category = getString(R.string.category_cold)
            ),
            Product(
                id = "10",
                name = getString(R.string.product_10_name),
                description = getString(R.string.product_10_desc),
                price = 199.99,
                category = getString(R.string.category_cold)
            ),
            Product(
                id = "11",
                name = getString(R.string.product_11_name),
                description = getString(R.string.product_11_desc),
                price = 399.99,
                category = getString(R.string.category_allergy)
            ),
            Product(
                id = "12",
                name = getString(R.string.product_12_name),
                description = getString(R.string.product_12_desc),
                price = 449.99,
                category = getString(R.string.category_allergy)
            ),
            Product(
                id = "13",
                name = getString(R.string.product_13_name),
                description = getString(R.string.product_13_desc),
                price = 1499.99,
                category = getString(R.string.category_firstaid),
                isPopular = true
            ),
            Product(
                id = "14",
                name = getString(R.string.product_14_name),
                description = getString(R.string.product_14_desc),
                price = 299.99,
                category = getString(R.string.category_firstaid)
            ),
            Product(
                id = "15",
                name = getString(R.string.product_15_name),
                description = getString(R.string.product_15_desc),
                price = 249.99,
                category = getString(R.string.category_firstaid)
            ),
            Product(
                id = "16",
                name = getString(R.string.product_16_name),
                description = getString(R.string.product_16_desc),
                price = 699.99,
                category = getString(R.string.category_vitamins)
            ),
            Product(
                id = "17",
                name = getString(R.string.product_17_name),
                description = getString(R.string.product_17_desc),
                price = 449.99,
                category = getString(R.string.category_vitamins)
            ),
            Product(
                id = "18",
                name = getString(R.string.product_18_name),
                description = getString(R.string.product_18_desc),
                price = 399.99,
                category = getString(R.string.category_vitamins)
            ),
            Product(
                id = "19",
                name = getString(R.string.product_19_name),
                description = getString(R.string.product_19_desc),
                price = 799.99,
                category = getString(R.string.category_vitamins),
                isPopular = true
            ),
            Product(
                id = "20",
                name = getString(R.string.product_20_name),
                description = getString(R.string.product_20_desc),
                price = 599.99,
                category = getString(R.string.category_vitamins)
            )
        )
    }

    private fun handleProductClick(product: Product) {
        // Save to purchase history
        purchaseRepository.addPurchase(product)
        
        // Show toast message
        val message = getString(R.string.add_to_cart_message, product.name)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 