package com.epitech.cashmanagerinterface.features.cart

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.epitech.cashmanagerinterface.common.data.CartItem
import com.epitech.cashmanagerinterface.common.data.Product

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: MutableList<CartItem> get() = _cartItems
    fun setCart(itemList: MutableList<CartItem>) {
        _cartItems.clear()
        for (item in itemList) {
            addToCart(item.product, 1)
        }
    }

    fun addToCart(product: Product, quantity: Int) {
        val existingItem = _cartItems.find { it.product == product }

        if (existingItem != null) {
            existingItem.quantity += quantity
        } else {
            val cartItem = CartItem(product, quantity)
            _cartItems.add(cartItem)
        }
    }

    fun getAllProductsInCart(): List<CartItem> {
        return _cartItems.toList()
    }

    private fun updateCartItem(cartItem: CartItem, newQuantity: Int) {
        val index = _cartItems.indexOf(cartItem)
        if (index != -1) {
            _cartItems[index] = cartItem.copy(quantity = newQuantity)
        }
    }

    fun removeCartItem(cartItem: CartItem) {
        _cartItems.remove(cartItem)
    }

    fun decreaseQuantity(cartItem: CartItem) {
        if (cartItem.quantity > 1) {
            val newQuantity = cartItem.quantity - 1
            updateCartItem(cartItem, newQuantity)
        }
    }

    fun increaseQuantity(cartItem: CartItem) {
        val newQuantity = cartItem.quantity + 1
        updateCartItem(cartItem, newQuantity)
    }

    fun calculateTotalPrice(cartItem: CartItem): String {
        val totalPrice = cartItem.product.price * cartItem.quantity
        return String.format("%.2f", totalPrice)
    }

    fun calculateTotalCartPrice(): Float {
        return _cartItems.sumOf { calculateTotalPrice(it).toDouble() }.toFloat()
    }
}