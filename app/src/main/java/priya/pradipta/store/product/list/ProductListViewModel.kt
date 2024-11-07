package priya.pradipta.store.product.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import priya.pradipta.common.ResultOf
import priya.pradipta.store.domain.usecase.GetProducts
import priya.pradipta.store.product.model.ProductModel

class ProductListViewModel(
    private val getProducts: GetProducts,
) : ViewModel() {
    private val _uiState: MutableStateFlow<ProductListUIState> =
        MutableStateFlow(
            ProductListUIState.Initial,
        )
    val uiState: StateFlow<ProductListUIState> = _uiState.asStateFlow()

    init {
        getAllProducts()
    }

    fun getAllProducts() {
        _uiState.value = ProductListUIState.Loading
        viewModelScope.launch {
            getProducts().also { result ->
                Log.d("Teeeeeeeeeeest", "doLogin: $result")
                when (result) {
                    is ResultOf.Success -> {
                        _uiState.value =
                            ProductListUIState.OnSuccess(
                                result.data.map { product ->
                                    ProductModel(
                                        name = product.title.toString(),
                                        price = product.price.toString(),
                                        image = product.image.toString(),
                                        category = product.category.toString(),
                                        rating = product.rating?.rate.toString(),
                                        count = product.rating?.count.toString(),
                                        description = product.description.toString(),
                                    )
                                },
                            )
                    }

                    is ResultOf.Failure -> {
                        _uiState.value =
                            ProductListUIState.OnFailure(
                                message = result.exception.message ?: "Uncaught Error",
                            )
                    }
                }
            }
        }
    }
}
