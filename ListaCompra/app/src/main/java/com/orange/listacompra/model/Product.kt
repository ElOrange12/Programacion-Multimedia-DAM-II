package com.orange.listacompra.model

/**
 * Producto de la lista de la compra.
 *
 * @property id identificador único, usado como clave estable en la LazyColumn.
 * @property name nombre del producto.
 * @property isPurchased `true` si ya se ha comprado, `false` si sigue pendiente.
 */
data class Product(
    val id: Long,
    val name: String,
    val isPurchased: Boolean = false
)
