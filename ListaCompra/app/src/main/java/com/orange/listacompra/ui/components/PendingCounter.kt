package com.orange.listacompra.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import com.orange.listacompra.R

/** Contador de productos pendientes, con la forma singular/plural correcta del recurso `<plurals>`. */
@Composable
fun PendingCounter(
    pendingCount: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = pluralStringResource(R.plurals.pending_products, pendingCount, pendingCount),
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary
    )
}
