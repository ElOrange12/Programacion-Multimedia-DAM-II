package com.orange.listacompra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.orange.listacompra.ui.ListaCompraScreen
import com.orange.listacompra.ui.theme.ListaCompraTheme
import com.orange.listacompra.viewmodel.ProductListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListaCompraTheme {
                val viewModel: ProductListViewModel = viewModel()
                ListaCompraScreen(
                    products = viewModel.products,
                    pendingCount = viewModel.pendingCount,
                    onAddProduct = viewModel::addProduct,
                    onTogglePurchased = viewModel::togglePurchased,
                    onRemoveProduct = viewModel::removeProduct
                )
            }
        }
    }
}
