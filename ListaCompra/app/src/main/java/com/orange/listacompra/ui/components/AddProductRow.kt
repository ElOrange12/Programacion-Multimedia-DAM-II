package com.orange.listacompra.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.orange.listacompra.R

/**
 * Fila superior con el campo de texto y el botón "Añadir".
 *
 * @param onAddProduct recibe el texto escrito y devuelve `true` si el producto se ha añadido
 * (es decir, si el texto era válido). Si devuelve `false` se muestra un error en el campo.
 */
@Composable
fun AddProductRow(
    onAddProduct: (String) -> Boolean,
    modifier: Modifier = Modifier
) {
    var name by rememberSaveable { mutableStateOf("") }
    var showError by rememberSaveable { mutableStateOf(false) }

    val submit = {
        if (onAddProduct(name)) {
            name = ""
            showError = false
        } else {
            showError = true
        }
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_small)),
        verticalAlignment = Alignment.Top
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                showError = false
            },
            modifier = Modifier.weight(1f),
            placeholder = { Text(text = stringResource(R.string.product_name_placeholder)) },
            singleLine = true,
            isError = showError,
            supportingText = if (showError) {
                { Text(text = stringResource(R.string.error_empty_name)) }
            } else {
                null
            },
            shape = MaterialTheme.shapes.large,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { submit() })
        )
        Button(
            onClick = { submit() },
            modifier = Modifier.height(OutlinedTextFieldDefaults.MinHeight)
        ) {
            Text(text = stringResource(R.string.add_button))
        }
    }
}
