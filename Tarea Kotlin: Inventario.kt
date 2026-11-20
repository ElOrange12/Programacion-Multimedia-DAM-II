fun main() {
    val stock = listOf(25, 0, 8, -3, 50, 3)

    val proveedor: String? = null

    println("Proveedor: ${proveedor ?: "Sin proveedor"}")

    var agotados = 0
    var stockBajo = 0
    var totalUnidades = 0
    var productosValidos = 0

    for ((indice, unidades) in stock.withIndex()) {
        val numProducto = indice + 1

        if (unidades < 0) {
            println("Producto $numProducto: Dato erróneo")
            continue
        }

        val estado = when {
            unidades == 0 -> {
                agotados++
                "Agotado"
            }
            unidades in 1..9 -> {
                stockBajo++
                "Stock bajo"
            }
            unidades in 10..29 -> "Stock normal"
            else -> "Stock alto"
        }

        totalUnidades += unidades
        productosValidos++

        println("Producto $numProducto: $unidades uds ($estado)")
    }

    val media = if (productosValidos > 0) totalUnidades / productosValidos else 0

    val mensajePedido = if (agotados > 0 || stockBajo > 0) {
        "Hay que hacer un pedido."
    } else {
        "No hace falta pedido."
    }

    println("Agotados: $agotados. Stock bajo: $stockBajo. Total: $totalUnidades uds. Media: $media. $mensajePedido")
}
