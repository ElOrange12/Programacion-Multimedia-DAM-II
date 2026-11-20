package com.orange.listacompra.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.orange.listacompra.model.Product

/**
 * Gestiona el estado de la lista de la compra. Sobrevive a los cambios de configuración
 * (por ejemplo, al rotar la pantalla).
 */
class ProductListViewModel : ViewModel() {

    var products by mutableStateOf<List<Product>>(emptyList())
        private set

    /** Número de productos que todavía no se han comprado. Se recalcula con cada cambio de la lista. */
    val pendingCount: Int
        get() = products.count { !it.isPurchased }

    private var nextId = 0L

    /**
     * Añade un producto al final de la lista.
     *
     * @return `false` (sin añadir nada) si el nombre está vacío o solo tiene espacios; `true` en otro caso.
     */
    fun addProduct(name: String): Boolean {
        val trimmedName = name.trim()
        if (trimmedName.isEmpty()) return false

        products = products + Product(id = nextId++, name = trimmedName)
        Log.d(TAG, "Producto añadido: \"$trimmedName\" (${products.size} en la lista)")
        return true
    }

    /** Alterna un producto entre pendiente y comprado. */
    fun togglePurchased(productId: Long) {
        products = products.map { product ->
            if (product.id == productId) product.copy(isPurchased = !product.isPurchased) else product
        }
        products.firstOrNull { it.id == productId }?.let { product ->
            Log.d(TAG, "Producto \"${product.name}\" marcado como ${if (product.isPurchased) "comprado" else "pendiente"}")
        }
    }

    /** Elimina un producto de la lista. */
    fun removeProduct(productId: Long) {
        val removedProduct = products.firstOrNull { it.id == productId } ?: return
        products = products.filterNot { it.id == productId }
        Log.d(TAG, "Producto eliminado: \"${removedProduct.name}\" (${products.size} en la lista)")
    }

    private companion object {
        const val TAG = "ListaCompra"
    }
}
