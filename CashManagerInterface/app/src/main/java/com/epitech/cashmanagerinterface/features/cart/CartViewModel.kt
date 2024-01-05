package com.epitech.cashmanagerinterface.features.cart

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.epitech.cashmanagerinterface.common.data.CartItem
import com.epitech.cashmanagerinterface.common.data.Product

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: MutableList<CartItem> get() = _cartItems

    fun addToCart(product: Product, quantity: Int) {
        val existingItem = _cartItems.find { it.product == product }

        if (existingItem != null) {
            existingItem.quantity += quantity
        } else {
            val cartItem = CartItem(product, quantity)
            _cartItems.add(cartItem)
            println("Product: ${cartItem.product.name}, Quantity: ${cartItem.quantity}")
        }
    }

    fun updateCartItem(cartItem: CartItem, newQuantity: Int) {
        val index = _cartItems.indexOf(cartItem)
        if (index != -1) {
            _cartItems[index] = cartItem.copy(quantity = newQuantity)
        }
    }

    fun removeCartItem(cartItem: CartItem) {
        _cartItems.remove(cartItem)
    }

    fun calculateTotalPrice(cartItem: CartItem): String {
        val totalPrice = cartItem.product.price * cartItem.quantity
        return String.format("%.2f", totalPrice)
    }

    fun calculateTotalCartPrice(): Float {
        return _cartItems.sumOf { calculateTotalPrice(it).toDouble() }.toFloat()
    }
}