package com.orange.listacompra.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.orange.listacompra.R
import com.orange.listacompra.model.Product
import com.orange.listacompra.ui.components.AddProductRow
import com.orange.listacompra.ui.components.EmptyState
import com.orange.listacompra.ui.components.PendingCounter
import com.orange.listacompra.ui.components.ProductCard
import com.orange.listacompra.ui.theme.ListaCompraTheme

/**
 * Pantalla principal de la lista de la compra. No guarda estado de negocio: recibe los datos
 * y notifica las acciones del usuario mediante callbacks.
 */
@Composable
fun ListaCompraScreen(
    products: List<Product>,
    pendingCount: Int,
    onAddProduct: (String) -> Boolean,
    onTogglePurchased: (Long) -> Unit,
    onRemoveProduct: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(dimensionResource(R.dimen.screen_margin))
        ) {
            Text(
                text = stringResource(R.string.screen_title),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(dimensionResource(R.dimen.spacing_small)))
            AddProductRow(onAddProduct = onAddProduct)
            Spacer(Modifier.height(dimensionResource(R.dimen.spacing_small)))
            PendingCounter(pendingCount = pendingCount)
            Spacer(Modifier.height(dimensionResource(R.dimen.spacing_small)))

            if (products.isEmpty()) {
                EmptyState(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_small))
                ) {
                    items(items = products, key = { it.id }) { product ->
                        ProductCard(
                            product = product,
                            onTogglePurchased = { onTogglePurchased(product.id) },
                            onRemove = { onRemoveProduct(product.id) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListaCompraScreenEmptyPreview() {
    ListaCompraTheme {
        ListaCompraScreen(
            products = emptyList(),
            pendingCount = 0,
            onAddProduct = { true },
            onTogglePurchased = {},
            onRemoveProduct = {}
        )
    }
}
