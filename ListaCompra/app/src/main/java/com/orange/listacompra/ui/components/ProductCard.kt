package com.orange.listacompra.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import com.orange.listacompra.R
import com.orange.listacompra.model.Product

/**
 * Tarjeta de un producto: nombre, icono de estado en la esquina superior derecha (dentro de un Box)
 * y botones para cambiar el estado o eliminar el producto.
 */
@Composable
fun ProductCard(
    product: Product,
    onTogglePurchased: () -> Unit,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    val containerColor = if (product.isPurchased) colors.primaryContainer else colors.surfaceContainerHigh
    val contentColor = if (product.isPurchased) colors.onPrimaryContainer else colors.onSurface

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.card_content_padding))
        ) {
            StatusIcon(
                isPurchased = product.isPurchased,
                modifier = Modifier.align(Alignment.TopEnd)
            )
            Column {
                Text(
                    text = product.name,
                    modifier = Modifier.padding(
                        end = dimensionResource(R.dimen.status_icon_size) +
                            dimensionResource(R.dimen.spacing_small)
                    ),
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (product.isPurchased) TextDecoration.LineThrough else null
                )
                ProductActions(
                    productName = product.name,
                    isPurchased = product.isPurchased,
                    onTogglePurchased = onTogglePurchased,
                    onRemove = onRemove
                )
            }
        }
    }
}

@Composable
private fun StatusIcon(
    isPurchased: Boolean,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = if (isPurchased) Icons.Filled.CheckCircle else Icons.Outlined.ShoppingCart,
        contentDescription = stringResource(
            if (isPurchased) R.string.status_purchased else R.string.status_pending
        ),
        modifier = modifier.size(dimensionResource(R.dimen.status_icon_size)),
        tint = if (isPurchased) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        }
    )
}

@Composable
private fun ProductActions(
    productName: String,
    isPurchased: Boolean,
    onTogglePurchased: () -> Unit,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = onTogglePurchased) {
            Icon(
                imageVector = if (isPurchased) Icons.Filled.Refresh else Icons.Filled.Check,
                contentDescription = stringResource(
                    if (isPurchased) R.string.mark_as_pending else R.string.mark_as_purchased,
                    productName
                )
            )
        }
        IconButton(
            onClick = onRemove,
            colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.error)
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = stringResource(R.string.delete_product, productName)
            )
        }
    }
}
